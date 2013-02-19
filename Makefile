
SOURCE = Torrganizer.java \
	TFile.java \
	MovieFile.java \
	ShowFile.java \
	CLInterface.java

all: $(SOURCE)
	-@echo Building Torrganizer...
	javac -Xlint Torrganizer.java

debug: $(COURSE)
	javac -Xlint -g Torrganizer.java


clean:
	rm *.class

files: $(SOURCE)
	rm -rf files/*
	rm -rf Sort/*
	./createFiles.sh
