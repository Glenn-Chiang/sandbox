# sandbox
A simple falling sand simulation system build in Java with [libgdx](https://github.com/libgdx/libgdx).  
The simulation features a 2D sandbox world in which users can create various elements like sand, water and fire which behave differently and interact with each other in interesting ways.

## Concept
The fundamental idea behind the falling sand algorithm is based on the principles of [cellular automata](https://en.wikipedia.org/wiki/Cellular_automaton). A cellular automaton typically consists of a matrix of discrete cells each containing a particular state that evolves over time. In each cycle of iteration, each cell updates its state according to a predefined set of rules which determine the next state of the cell based on its current state and the states of its neighbours.

In the case of our falling sand simulation, the sandbox world is represented by a grid of cells, which can either be empty or contain a particular element. Each element follows a unique set of rules that govern its behaviour and the ways in which it interacts with its surrounding elements. For example, sand falls downward if there is an empty cell below it, or falls diagonally to the left or right if those cells are empty. Water, on the other hand, behaves similarly to sand except that it also attempts to flow left or right, producing the effect of fluidity. Fire burns flammable elements like wood and oil, while acid corrodes most other elements. 

Unlike a classical cellular automaton where each cell contains a single state value, our simulation grid contains instantiated element objects which can encapsulate multiple state fields that can change over time. Having an "integrity" field that represents the current physical condition of the element allows us to create more complex behaviours such as burning in fire or corrosion in acid as the integrity of the element instance diminishes over time before it is destroyed.

As we add more kinds of elements to the simulation, their increasingly complex interactions may give rise to potentially fascinating emergent behaviour and visual spectacles. This project serves no purpose beyond providing some entertainment to the user by bestowing them the power of a God who can create and destroy the natural elements at will within a virtual sandbox world.

## Getting started

