set PATH=C:\Java\jdk1.8.0_73\bin;C:\Java\jdk1.8.0_77\bin;%PATH%
set classpath=src\lib\Base64.jar;src\lib\GraphLayout.jar;src\lib\bcel.jar;\src\lib\http.jar;src\lib\iiop.jar;src\lib\jade.jar;src\lib\jadeTools.jar;src\lib\jadex_examples.jar;src\lib\jadex_jadeadapter.jar;src\lib\jadex_rt.jar;src\lib\jadex_standalone.jar;src\lib\jadex_tools.jar;src\lib\jhall.jar;src\lib\jibx-bind.jar;src\lib\jibx-extras.jar;src\lib\jibx-run.jar;src\lib\xpp3.jar;src;src\lib\commons-commons\commons-codec-1.3.jar

dir /s /B *.java > sources.txt
javac @sources.txt

jar cvf CatanJadex.jar *


set PATH=C:\Program Files\Java\jdk-15.0.1\bin;%PATH%
set classpath=CatanJadex.jar;src\lib\Base64.jar;src\lib\GraphLayout.jar;src\lib\bcel.jar;\src\lib\http.jar;src\lib\iiop.jar;src\lib\jade.jar;src\lib\jadeTools.jar;src\lib\jadex_examples.jar;src\lib\jadex_jadeadapter.jar;src\lib\jadex_rt.jar;src\lib\jadex_standalone.jar;src\lib\jadex_tools.jar;src\lib\jhall.jar;src\lib\jibx-bind.jar;src\lib\jibx-extras.jar;src\lib\jibx-run.jar;src\lib\xpp3.jar;src;src\lib\commons-commons\commons-codec-1.3.jar

java jadex.adapter.standalone.Platform
pause