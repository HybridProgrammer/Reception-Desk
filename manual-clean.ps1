Remove-Item -Recurse -Force "C:\Users\jheithof\.grails\2.2.1\projects\reception-desk\plugin-classes"

$tomcat = netstat -nao | select-string -pattern "Listen" | select-string -pattern "\[::\]:8080" | %{ $_.ToString().Split(' ') }
Stop-Process $tomcat[$tomcat.length-1]

return 0