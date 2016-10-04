/* tut1.c
 * Reads pass.dat and prints the data
 * surrounded by X's.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#include "getresponse.c"

/* Constant definitions */

#define USER_LEN   20
#define PWD_LEN    20

int main() {

  char  buffer[20];
  FILE *passfile;

  /* Open pass.dat.  If not successful, print
   * an error messge and exit. 
   */

  if (0 == (passfile = fopen("pass.dat", "r"))) {
    printf("Cannot open pass.dat\n");
    printf("Program exiting\n");
    exit(1);
  }

  /* Read the data from the file
   * and print it.
   */

  getresponse((char *) buffer, sizeof(buffer), passfile);
  printf("X%sX\n", buffer);
  getresponse((char *) buffer, sizeof(buffer), passfile);
  printf("X%sX\n", buffer);

  /* Close the file. */

  fclose(passfile);

  return(0);
}
