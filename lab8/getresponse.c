/* GETRESPONSE - This is a function for safely reading input
                 from a file or the keyboard.

   buffer -  is a character array for storing the data read
   limit -   is the size of the buffer.  WARNING: This number
             must not exceed the size of the buffer declared
	     in the calling function.
   *whence - is where to read data from.  It can be an
             opened file pointer or could be stdin
*/

int getresponse (char buffer[], int limit, FILE *whence) {
  int c, i =0 ;
  /* While we have not reached the end of the file or the
     end of a line: get a character; decrement limit; 
     if limit is greater than zero, put the character into
     the buffer; if limit does hit zero, output a message
     that we are truncating the input. */
  while ( ((c = getc(whence)) != EOF) && (c != '\n') ) {
    if (--limit > 0) {
      buffer[i++] = c;
    }
    if (limit == 0) {
      fprintf(stderr, "Warning: input truncated to length %d\n", i);
    }
  }

  /* Add the string terminator and return the number of characters.*/
  buffer[i] = '\0';
  return i;
}







