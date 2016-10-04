#
# A generic Makefile to compile an embedded SQL program.
#
#  CAUTION - to use this Makefile, you must either
#    a) set the environmental variable "exe" to the name of the program
#    you wish to compile, which *MUST* be the same name as your embedded
#    .pc file
#  (You do not have to reset exe until you have your program compiled.)
#
#      setenv exe <filename>
#      make <filename>
#  OR
#
#    b) include it on the make command line (filename *MUST* be the same
#    name as the .pc file you are using as your embedded sql source file).
#
#      make <filename> exe=<filename>
#

include /oracle/ora112g/cosc344/orahome/precomp/lib/env_precomp.mk

.SUFFIXES: .pc .c .o

PROCPLSFLAGS= sqlcheck=full userid=$(USERID)

INCLUDE=-I. -I/usr/lib/gcc/x86_64-redhat-linux/5.3.1/include -I/oracle/ora112g/cosc344/orahome/precomp/public -I/oracle/ora112g/cosc344/orahome/rdbms/public -I/oracle/ora112g/cosc344/orahome/rdbms/demo

$(exe): $(exe).o
	$(CC) -o $@ $@.o $(OBJS) -L$(LIBHOME) $(PROLDLIBS)

.pc.o:
	$(PROC) $(PROCFLAGS) iname=$*.pc
	$(CC) $(CFLAGS) -c $*.c

.pc.c:
	$(PROC) $(PROCFLAGS) iname=$*.pc

.c.o:
	$(CC) $(CFLAGS) -c $*.c

