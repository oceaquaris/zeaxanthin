COMPILER=javac

CFLAGS=-Xlint

APACHE_COMMONS_LANG3=/usr/share/java/apache-commons-lang3.jar
OPENCSV=/home/rs14/zeaxanthin/opencsv-3.8.jar

CLASSPATHS=-cp .:$(APACHE_COMMONS_LANG3):$(OPENCSV)
SOURCEPATHS=-sourcepath /home/rs14/zeaxanthin

JAVASOURCES:=$(patsubst %.java,%.class,$(wildcard *.java))

all: $(JAVASOURCES)

%.class: %.java
	@printf "Compiling %s...\n" $@
	$(COMPILER) $(CFLAGS) $(CLASSPATHS) $(SOURCEPATHS) $<
	@printf "%s compiled.\n" $@

clean:
	rm -f *.class
