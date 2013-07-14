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

package reception.desk

class FunctionController {

    static scaffold = true

    def index() {
		redirect(action: "list")
	}
	
	def list(Integer max) {
		params.max = Math.min(max ?: 25, 100)
		[functionInstanceList: Function.list(params), functionInstanceTotal: Function.count()]
	}
}
