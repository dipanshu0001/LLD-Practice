# Elevator System - Logging Documentation

## 🎯 Overview
The elevator system now includes comprehensive logging to simulate and track real elevator operations. The logging shows elevator movement, request handling, and the distance + direction based selection algorithm in action.

## 📊 Logging Features

### 1. **Initialization Logging**
```
🏢 [INIT] Elevator 1 initialized at floor 0
```
Shows when each elevator controller is created and its starting floor.

### 2. **Request Addition Logging**
```
📞 [REQUEST] Elevator 1 received request for floor 5
   Current floor: 0 | Direction: IDLE
   ✅ Added to UP queue: [5]
```
Logs when a request is added, showing:
- Target floor
- Current elevator state
- Which queue (UP/DOWN) the request was added to

### 3. **Request Processing Logging**
```
⚙️  [PROCESSING] Elevator 1 processing next request...
   Current State: Floor 0 | Direction: IDLE
   Pending UP requests: [3, 5, 7]
   Pending DOWN requests: []
```
Shows the elevator's decision-making process before handling a request.

### 4. **Movement Simulation**
```
🚀 [MOVING] Elevator 1 starting movement...
   From: Floor 0 → To: Floor 5
   ⬆️  Passing floor 1...
   ⬆️  Passing floor 2...
   ⬆️  Passing floor 3...
   ⬆️  Passing floor 4...
   ⬆️  Passing floor 5...
   🛑 [ARRIVED] Elevator 1 arrived at floor 5
   🚪 Doors opening...
   🚪 Doors closing...
```
Simulates actual elevator movement:
- Shows each floor passed (with 0.5 second delay per floor)
- Indicates direction with arrows (⬆️ or ⬇️)
- Simulates door operations (1 second delay)

### 5. **Direction Changes**
```
🔄 Switching direction from UP to DOWN
```
Logs when the elevator changes direction after completing all requests in one direction.

### 6. **Elevator Selection Logging**
```
🔍 [SELECTION] Finding best elevator for floor 3
   Total elevators available: 3

   Evaluating each elevator:

   📊 Elevator 1:
      Current Floor: 0
      Current Direction: IDLE
      Distance: 3 floors
      🧮 Calculating priority score:
         Request Floor: 3
         Elevator Floor: 0
         Direction: IDLE
         Distance: 3
         → IDLE elevator - Score = 3
      Priority Score: 3
      ✨ First candidate!

   📊 Elevator 2:
      Current Floor: 5
      Current Direction: UP
      Distance: 2 floors
      🧮 Calculating priority score:
         Request Floor: 3
         Elevator Floor: 5
         Direction: UP
         Distance: 2
         → Moving AWAY from request ✗
         → Score = 2 + 1000 (penalty) = 1002
      Priority Score: 1002
      ❌ Not better than current best

   🏆 SELECTED: Elevator 1 (Score: 3)
```
Detailed selection process showing:
- All available elevators evaluated
- Each elevator's current state
- Priority score calculation with reasoning
- Final selection decision

### 7. **Priority Score Calculation**
The algorithm evaluates elevators based on:
- **IDLE elevators**: Score = distance (neutral)
- **Moving towards request**: Score = distance (optimal)
- **Moving away from request**: Score = distance + 1000 (penalty)

Lower score = higher priority

### 8. **State Change Logging**
```
🔧 Elevator 1 floor updated: 0 → 5
🔧 Elevator 1 direction updated: IDLE → UP
```
Tracks manual state changes made to elevators.

### 9. **Status Checks**
```
ℹ️  Elevator 1 has requests: true
📏 Elevator 1 distance to floor 5: 5 floors
🎯 Elevator 1 moving towards floor 5: true
```
Logs utility method calls for debugging.

## 🚀 Running the Interactive Demo

To run the interactive elevator system:

```bash
cd "src"
javac -d ../out Model/*.java Enum/*.java Service/*.java ElevatorSimulationDemo.java
java -cp ../out ElevatorSimulationDemo
```

### Interactive Commands

Once the application starts, you can use the following commands:

- **Enter a floor number (0-20)**: Request an elevator to that floor
  - Example: `5` - Request elevator to floor 5
  - You'll then be prompted for a destination floor
  
- **`status`**: Display current state of all elevators
  - Shows floor, direction, pending requests for each elevator
  
- **`exit` or `quit`**: Shutdown the elevator system gracefully

### How It Works

1. **Background Processing**: Each elevator runs in a separate thread, continuously checking and processing requests every 2 seconds
2. **Real-time Request Handling**: When you request a floor, the system:
   - Evaluates all elevators using the distance + direction algorithm
   - Selects the best elevator
   - Asks for your destination floor
   - Adds both pickup and destination to the elevator's queue
3. **Concurrent Operation**: Multiple elevators can move simultaneously while you continue to add new requests

### Example Interaction

```
🎮 Enter command (floor number/status/exit): 5

════════════════════════════════════════════════════════════
📞 NEW REQUEST: Floor 5
════════════════════════════════════════════════════════════

🔍 [SELECTION] Finding best elevator for floor 5
   [Selection logs...]

✅ Request assigned to Elevator 1
📍 Enter destination floor: 10
✅ Route added: Floor 5 → Floor 10
⏳ Elevator will process the request shortly...

🎮 Enter command (floor number/status/exit): status

════════════════════════════════════════════════════════════
📊 CURRENT SYSTEM STATUS
════════════════════════════════════════════════════════════

🏢 Elevator 1:
   Floor: 2
   Direction: UP
   Status: ACTIVE
   Pending UP requests: [5, 10]
   Pending DOWN requests: []
   Has requests: true
```

## 📝 Example Output Structure

```
═══════════════════════════════════════════════════════════
🏢 ELEVATOR SYSTEM SIMULATION - Distance + Direction Based
═══════════════════════════════════════════════════════════

[Initialization logs...]

╔═══════════════════════════════════════════════════════════╗
║ TEST 1: Request from Floor 3                              ║
╚═══════════════════════════════════════════════════════════╝

[Selection logs...]
[Request addition logs...]
[Movement simulation logs...]

╔═══════════════════════════════════════════════════════════╗
║ TEST 2: Request from Floor 10                             ║
╚═══════════════════════════════════════════════════════════╝

[More tests...]

═══════════════════════════════════════════════════════════
✅ SIMULATION COMPLETE!
═══════════════════════════════════════════════════════════
```

## 🎨 Emoji Legend

- 🏢 Initialization
- 📞 Request received
- ⚙️  Processing
- 🚀 Movement start
- ⬆️  Moving up
- ⬇️  Moving down
- 🛑 Arrival
- 🚪 Door operation
- 🔄 Direction change
- 💤 Going idle
- 🔍 Selection process
- 📊 Elevator evaluation
- 🧮 Score calculation
- 🏆 Selected elevator
- ✨ New best candidate
- ❌ Rejected/skipped
- ✅ Success
- ⚠️  Warning
- ℹ️  Information
- 📏 Distance measurement
- 🎯 Direction check
- 🔧 State update

## 🔧 Customizing Logging

You can adjust the simulation timing in `ElevatorController.java`:
- `Thread.sleep(500)` - Time per floor (currently 0.5 seconds)
- `Thread.sleep(1000)` - Door operation time (currently 1 second)

To disable specific logs, comment out the relevant `System.out.println()` statements.


