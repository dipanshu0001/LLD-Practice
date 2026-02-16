# Elevator System UML Class Diagram

```mermaid
classDiagram
    class ElevatorSimulationDemo {
        +main(args: String[])
        -initializeElevatorSystem()
        -startElevatorProcessing()
        -runInteractiveLoop()
        -handlePassengerRequest(input: String)
        -handleInternalRequest(input: String)
        -displaySystemStatus()
    }

    class Building {
        -floors: List~Floor~
        -elevators: List~ElevatorController~
        +Building(numberOfFloor: int, numberOfElevators: int)
        -createElevators(numberOfElevators: int)
        -createFloors(numberOfFloor: int)
    }

    class Floor {
        -id: int
        -buttonPanel: ButtonPanel
        -waitingPassengers: Queue~Passenger~
        +Floor(id: int, buttonPanel: ButtonPanel)
        +addPassenger(passenger: Passenger)
        +getWaitingPassengers()
    }

    class Passenger {
        -id: int
        -sourceFloor: int
        -destinationFloor: int
        +Passenger(id: int, source: int, dest: int)
    }

    class ElevatorController {
        -elevator: Elevator
        -currentFloor: int
        -currentDirection: Direction
        -upRequests: PriorityQueue~Integer~
        -downRequests: PriorityQueue~Integer~
        -floors: List~Floor~
        +ElevatorController(elevator: Elevator, floors: List~Floor~)
        +addRequest(floor: int)
        +processNextRequest()
        -simulateMovement(from: int, to: int)
        -onboardPassengers(floorIndex: int)
    }

    class Elevator {
        -maxPeople: int
        -maxWeight: int
        -liftNumber: int
        -status: ElevatorStatus
        -elevatorContainer: ElevatorContainer
        +Elevator(...)
    }

    class ElevatorContainer {
        +doors: Door
        +buttonPanel: ButtonPanel
    }

    class Scheduler {
        -arrangeLift: ArrangeLift
        +Scheduler(arrangeLift: ArrangeLift)
        +getElevator(floor: Floor, elevators: List~ElevatorController~)
    }

    class ArrangeLift {
        <<interface>>
        +getElevator(floor: Floor, elevators: List~ElevatorController~)
    }

    class ShortestDistanceWithDirectionWay {
        +getElevator(floor: Floor, elevators: List~ElevatorController~)
        -calculatePriorityScore(...)
    }

    %% Relationships
    ElevatorSimulationDemo --> Building : creates
    ElevatorSimulationDemo --> Scheduler : uses
    
    Building *-- Floor : contains
    Building *-- ElevatorController : contains
    
    Floor o-- Passenger : contains queue of
    
    ElevatorController o-- Elevator : controls
    ElevatorController --> Floor : references (for onboarding)
    
    Elevator *-- ElevatorContainer : contains
    
    Scheduler o-- ArrangeLift : uses strategy
    ShortestDistanceWithDirectionWay ..|> ArrangeLift : implements
    
    ElevatorController ..> Passenger : picks up
```

## Description of Classes

*   **ElevatorSimulationDemo**: The main entry point. It initializes the building and scheduler, runs the simulation loop, and handles user input (creating passengers).
*   **Building**: Represents the physical building, containing lists of Floors and Elevators.
*   **Floor**: Represents a floor in the building. It has a queue of `waitingPassengers` who are waiting for an elevator.
*   **Passenger**: A simple data model representing a person with a Source floor and a Destination floor.
*   **ElevatorController**: The "Brain" of a specific elevator. It manages the state (current floor, direction), holds the request queues (`upRequests`, `downRequests`), moving logic, and now the `onboardPassengers` logic.
*   **Elevator**: The physical elevator attributes (capacity, weight, ID).
*   **Scheduler**: The central dispatcher. When a user requests an elevator, the Scheduler decides which ElevatorController is best suited to take the request.
*   **ArrangeLift (Interface) / ShortestDistanceWithDirectionWay**: The strategy pattern implementation for selecting the best elevator.
