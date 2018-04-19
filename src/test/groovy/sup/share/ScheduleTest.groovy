package sup.share;
import java.time.temporal.ChronoUnit
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

import javax.annotation.Resource

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.*
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.scheduling.annotation.SchedulingConfiguration
import org.springframework.stereotype.Component
import org.springframework.test.context.ContextConfiguration

import cn.supstore.core.base.exception.DataException
import cn.supstore.core.base.locker.LockerManager
import cn.supstore.core.base.locker.impl.RedisLockerManager
import cn.supstore.core.base.schedule.LockableTaskScheduler
import cn.supstore.core.base.schedule.SchedulerLock
import cn.supstore.core.config.RedisConfig
import spock.lang.Specification

/**
 * Created by liusijin on 2017/5/22.
 */
@Component
public class TestTask{

    public static boolean start1 = false;
    public static boolean start2 = false;
    public static Runnable job;
    CountDownLatch latch = new CountDownLatch(2);

    @Scheduled(cron = "0/3 * * * * ? ", zone = "Asia/Shanghai")
    @SchedulerLock(name="test1",expiry = 30,expiryUnit = ChronoUnit.SECONDS)
    public void test1(){
        if(start1){
            job.run();
            latch.await();
        }
    }

    @Scheduled(cron = "0/3 * * * * ?", zone = "Asia/Shanghai")
    @SchedulerLock(name="test1",expiry = 30,expiryUnit = ChronoUnit.SECONDS)
    public void test2(){
        if(start2){
            job.run();
            latch.await();
        }

    }
}

@ContextConfiguration(
        classes = [RedisConfig,RedisLockerManager,SchedulingConfiguration,LockableTaskScheduler,TestTask])
class ScheduleTest extends Specification{

    @Resource(name="redisTemplate")
    private RedisOperations<String, String> ops;

    @Autowired LockerManager mng;


    def "获取redis远程锁" (){
        when:
        ops.opsForValue().setIfAbsent("namekey","lalalala")

        then:
        ops.opsForValue().get("namekey") == "lalalala"
    }

    def "释放redis远程锁" (){
        when:
        ops.delete("namekey")

        then:
        ops.opsForValue().get("namekey")==null
    }

    def "多客户端只能有一个获取锁" (){
        when:"甲先获取test锁"
        def locker = mng.lockFor("test");
        assert locker.isPresent()

        then:"乙再尝试获取test锁失败"
        !mng.lockFor("test").isPresent();

        when:"甲释放test锁后"
        locker.get().release()

        then:"乙再获取，就会成功"
        mng.lockFor("test").isPresent();

        cleanup:
        ops.delete("test")
    }

    def "锁超时后，再释放会报运行错"(){
        when:"甲先获取5秒锁"
        def locker = mng.lockFor("5secLocker",TimeUnit.SECONDS.toMillis(3)).get();

        and:"等待5秒后"
        sleep(TimeUnit.SECONDS.toMillis(3))
        locker.release()

        then:"过期会报错"
        thrown DataException

        cleanup:
        ops.delete("5secLocker")
    }


    def "计划任务执行单个" (){
        setup:
        def run = Mock(Runnable);

        when:"开启两个任务"
        TestTask.class.job = run
        TestTask.class.start1=true
        TestTask.class.start2=true

        and:"等待3秒后"
        sleep(TimeUnit.SECONDS.toMillis(5))

        then:"只有一个可以执行"
        1 * run.run()

        cleanup:
        ops.delete("test1")
    }
}