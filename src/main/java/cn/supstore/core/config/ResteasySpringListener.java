package cn.supstore.core.config;

import org.jboss.resteasy.plugins.spring.SpringContextLoader;
import org.jboss.resteasy.plugins.spring.SpringContextLoaderSupport;
import org.jboss.resteasy.plugins.spring.i18n.Messages;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 * 使用annotation 启动的context来配置 监听器,放弃xml
 * ConfigurableWebApplicationContext
 */
class ResteasySpringListener extends ContextLoaderListener {

    public ResteasySpringListener(WebApplicationContext wac){
        super(wac);
    }

    private SpringContextLoaderSupport springContextLoaderSupport = new SpringContextLoaderSupport();

    @Override
    public void contextInitialized(ServletContextEvent event)
    {
        boolean scanProviders = false;
        boolean scanResources = false;

        String sProviders = event.getServletContext().getInitParameter("resteasy.scan.providers");
        if (sProviders != null)
        {
            scanProviders = Boolean.valueOf(sProviders.trim());
        }
        String scanAll = event.getServletContext().getInitParameter("resteasy.scan");
        if (scanAll != null)
        {
            boolean tmp = Boolean.valueOf(scanAll.trim());
            scanProviders = tmp || scanProviders;
            scanResources = tmp || scanResources;
        }
        String sResources = event.getServletContext().getInitParameter("resteasy.scan.resources");
        if (sResources != null)
        {
            scanResources = Boolean.valueOf(sResources.trim());
        }

        if (scanProviders || scanResources)
        {
            throw new RuntimeException(Messages.MESSAGES.cannotUseScanParameters());
        }


        super.contextInitialized(event);
    }

    protected ContextLoader createContextLoader()
    {
        return new SpringContextLoader();
    }

    @Override
    protected void customizeContext(ServletContext servletContext, ConfigurableWebApplicationContext configurableWebApplicationContext) {
        super.customizeContext(servletContext, configurableWebApplicationContext);
        this.springContextLoaderSupport.customizeContext(servletContext, configurableWebApplicationContext);
    }
}
