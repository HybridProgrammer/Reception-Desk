import reception.desk.MyAuthEventListener

// Place your Spring DSL code here
beans = {
    jmsConnectionFactory(org.apache.activemq.ActiveMQConnectionFactory) {
        brokerURL = 'vm://localhost'
    }
    myAuthEventListener(MyAuthEventListener)
    myUsernamePasswordAuthenticationFilter(MyUsernamePasswordAuthenticationFilter) {
        authenticationManager = ref('authenticationManager')
        //customService = ref('interchangeService')
        //springSecurityService = ref('springSecurityService')
        //myUsernamePasswordAuthenticationFilter = ref('myUsernamePasswordAuthenticationFilter')
    }
}
