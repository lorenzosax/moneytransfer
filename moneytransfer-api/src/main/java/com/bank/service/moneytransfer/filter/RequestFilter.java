package com.bank.service.moneytransfer.filter;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.bank.service.moneytransfer.controller.CustomerController;
import com.bank.service.moneytransfer.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;


@Component
public class RequestFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
            throws IOException, ServletException {
        MDC.put(Constant.REQUEST_ID, UUID.randomUUID().toString().replace("-", ""));
        logger.info("Call API: " + ((HttpServletRequest) request).getRequestURI());
        filterchain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterconfig) throws ServletException {
    }
}
