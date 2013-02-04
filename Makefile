SOURCE = Torrganizer.java

all: $(SOURCE)
	-@echo Building Torrganizer...
	javac -Xlint $(SOURCE)

clean:
	rm *.class
