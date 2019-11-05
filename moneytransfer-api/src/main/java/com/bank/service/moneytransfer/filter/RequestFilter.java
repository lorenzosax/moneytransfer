package com.bank.service.moneytransfer.filter;

import com.bank.service.moneytransfer.controller.CustomerController;
import com.bank.service.moneytransfer.utils.Constant;
import org.apache.catalina.connector.RequestFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

@Component
public class RequestFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
            throws IOException, ServletException {

        MDC.put(Constant.REQUEST_ID, UUID.randomUUID().toString().replace("-", ""));
        logger.info("Call API: " + ((RequestFacade) request).getRequestURI());
        filterchain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterconfig) throws ServletException {}
}
