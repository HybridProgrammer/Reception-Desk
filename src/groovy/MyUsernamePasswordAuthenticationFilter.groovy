
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


/**
 * Created with IntelliJ IDEA.
 * User: jason
 * Date: 7/8/13
 * Time: 10:15 PM
 * To change this template use File | Settings | File Templates.
 */
class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public org.springframework.security.core.Authentication attemptAuthentication(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
        return super.attemptAuthentication(request, response)

    }
}
