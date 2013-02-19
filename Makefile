
SOURCE = Torrganizer.java \
	TFile.java \
	MovieFile.java \
	ShowFile.java

all: $(SOURCE)
	-@echo Building Torrganizer...
	javac -Xlint Torrganizer.java

debug: $(COURSE)
	javac -Xlint -g Torrganizer.java


clean:
	rm *.class

files:
	rm -rf files/*
	./createFiles.sh
