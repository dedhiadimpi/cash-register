# Overview
Cash Register is an application that accepts $20, $10, $5, $2 and $1 bills. One can add, remove and view the denominations. When asked for a change, if there are sufficient bills of the correct denomination, it returns exact change in each denomination and if there are insufficient bills for exact change, it gives and exception.
# Features
1) <em>show</em> : Displays the current state of cash register in format: $<total> <# of 20’s> <# of 10’s> <# of 5’s> <# of 2’s> <# of 1’s>. If cash register is empty, displays "Cash register is empty" exception
  2) <em>put</em> : Adds the bills of each denomination to cash register and displays the current state of cash register. If input is not valid throws exception
  3) <em>take</em> : Removes the bills of each denomination from cash register and displays the current state of cash register. If input is not valid throws exception
  4) <em>change</em> : Returns the exact change in denomination. Also deducts the amount from cash register. If input is not valid throws exception
  5) <em>quit</em> : Exit the program 

# Dependency
Dependencies are used only for testing purpose.
All the dependencies are added in pom.xml
1) <em>JUnit</em> : For unit testing all the features
2) <em>Mockito and Power Mockito</em> : For mocking the bufferedReader object for unit testing the main function

# Example
<pre>
/*
* start program, waiting for command
*/
> ready

/*
* assume that the cash register was initialized with no bills.
* put bills in each denomination in: $20's $10's $5's $2's $1's
* then show the current state
*/
> put 1 2 3 4 5
$68 1 2 3 4 5

/*
* show the current state of the cash register
* with the total and each denomination.
*
* $Total $20's $10's $5's $2's $1's
* Total=$68 $20x1 $10x2 $5x3 $2x4 $1x5
*/
> show
$68 1 2 3 4 5

/*
* put bills in each denomination in: $20's $10's $5's $2's $1's
* then show the current state
*/
> put 1 2 3 0 5
$128 2 4 6 4 10

/*
* take bills in each denomination out: $20's $10's $5's $2's $1's
* then show the current state
*/
> take 1 4 3 0 10
$43 1 0 3 4 0

/*
* return change for a given amount by showing the number of each denomination the
vendor needs to return: $20's $10's $5's $2's $1's
* and remove money from the cash register
*/
> change 11
0 0 1 3 0 

/*
* if there is not enough funds in the register or no change can be made, show an
error
> change 14
Sorry

/*
* exit the program
*/
> quit
Bye
</pre>
