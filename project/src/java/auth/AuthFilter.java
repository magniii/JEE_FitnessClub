package auth;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author magniii
 */
public class AuthFilter implements Filter {

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    String beanName;
    String redirectURL;
    
    public AuthFilter() {
    }   
    
    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        String url = httpRequest.getRequestURI();
        if (!url.contains(redirectURL)){
            checkAndRedirect(httpRequest, httpResponse);
        }
        
        chain.doFilter(request, response);
    }

    public void destroy() {        
    }

    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        this.beanName = filterConfig.getInitParameter("beanName");
        this.redirectURL = filterConfig.getInitParameter("redirectURL");
    }
    
    private void checkAndRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException{
        AuthBean auth = (AuthBean) request.getSession().getAttribute(this.beanName);
        if (auth==null || !auth.isLoggedIn()) response.sendRedirect(this.redirectURL);
    }
}
