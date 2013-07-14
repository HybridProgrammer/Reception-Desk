
/*
 * Copyright 2013 Jason Heithoff
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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
