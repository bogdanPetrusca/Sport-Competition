default: build

build:
	javac -g *.java

run:
	java Main ${COMANDA} ${IN1} ${IN2} ${OUT}

clean:
	-rm *.class
