# Elevator System Flow Diagram

```mermaid
graph TD
    User([User / Console]) -->|Input: request 0 5| Demo[ElevatorSimulationDemo]
    
    subgraph Initialization
        Demo -->|Create| Building
        Building -->|Create| FloorList[List<Floor>]
        Building -->|Create| ElevatorList[List<ElevatorController>]
        ElevatorList -.->|Reference| FloorList
    end

    subgraph Request Handling
        Demo -->|1. Create Passenger| Passenger[Passenger (Src:0, Dst:5)]
        Demo -->|2. Add to Queue| Floor0[Floor 0 WaitingQueue]
        Passenger --> Floor0
        
        Demo -->|3. Find Best Elevator| Scheduler
        Scheduler -->|Selects| VC[ElevatorController]
        Demo -->|4. Add Pickup Request| VC
    end

    subgraph Elevator Processing Loop (Background Thread)
        VC -->|Check Requests| RequestQueue
        VC -->|Move| Hardware[Simulate Movement]
        Hardware -->|Arrive at Floor 0| Onboard[onboardPassengers(0)]
        
        Onboard -->|Check| Floor0
        Floor0 -->|Poll Passenger| VC
        
        Onboard -->|Auto-Add Dest Request| RequestQueue
        RequestQueue -->|New Goal: Floor 5| VC
    end

    subgraph Trip Completion
        VC -->|Move to Dest| Hardware
        Hardware -->|Arrive at Floor 5| Offboard[Drop Off]
        Offboard -->|Log| Console
    end
```

## detailed Flow Description

1.  **User Input**: The user enters `request <pickup> <destination>` in the console.
2.  **Passenger Creation**: The system creates a `Passenger` object with the source and destination details.
3.  **Queuing**: The passenger is placed in the `waitingPassengers` queue of the **Source Floor**.
4.  **Dispatch**: The `Scheduler` picks the best elevator and tells it to go to the **Source Floor**.
5.  **Pickup (The "Real Life" Part)**:
    *   The Elevator arrives at the Source Floor.
    *   It looks at the Floor's queue.
    *   It picks up the passenger.
    *   It **automatically** presses the button for the **Destination Floor** (adding it to the elevator's internal queue).
6.  **Drop-off**: The elevator travels to the destination and completes the trip.
