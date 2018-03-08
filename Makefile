.PHONY: build clean run

build: tema2

run:
	java -Xmx1G MainClass 

tema2:
	javac *.java

clean:
	rm -rf *.class
