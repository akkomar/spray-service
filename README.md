
Starting server:

 $ sbt

 > ~re-start

to stop:

 > re-stop


Testing multiple requests:

$ for i in {1..20} ;do (curl http://localhost/run/user/application/function &) ;done