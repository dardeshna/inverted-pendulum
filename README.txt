                         invertedpendulum README

Introduction
--------------------

This program graphically simulates an inverted pendulum and balances it using
feedback control loops.  The inverted pendulum and its motion are represented by
a Pendulum class, and the inverted pendulum is balanced by two PD loops, one to
control angle and one to control position from the start.


Specification
--------------------

This program meets the requirements specified.  It successfully simulates the
motion of an inverted pendulum and uses a feedback control loop to keep the
pendulum balanced and upright.

Errors
--------------------

There are no notable errors in the program that have been found thus far.  The
program has performed as expected for all test cases.

Overview of Code
--------------------

The Pendulum class represents an inverted pendulum and its motion, including
real-time acceleration, position and velocity of the pendulum and cart.  A
horizontal force may be applied to the cart through the update() method,
and this is how the feedback control loops keep the cart upright.

The Controller class balances the inverted pendulum by using two PD loops, one
to control angle and one to control position from the start.  The update()
method of the Pendulum is called 120 times per second, and the force passed in
is the output of the sum of the two PD loops, in turn keeping the inverted
pendulum upright.

The PD class implements a simple PD loop with tunable constants and parameters
for position and velocity.

Major Challenges
--------------------

The major challenge for this project was understanding the physics behind the
motion of the pendulum.  The free body diagram was fairly straightforward, and
the equations representing the motion of the pendulum were easily implementable.
However, the derivation of those equations was much more complex and required
the use of differential calculus, which was slightly beyond me.

Acknowledgements
--------------------

I would like to thank Mr. Kuszmaul for giving me the idea for this project. As
always, I would also like to thank my family for supporting me while I work on
time-consuming projects such as this.