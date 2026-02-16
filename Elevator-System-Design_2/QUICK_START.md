# Elevator System - Quick Start Guide

## 🎯 What You'll Experience

An interactive elevator system simulation that runs continuously, accepting floor requests from users while elevators operate in the background using a smart distance + direction based selection algorithm.

## 🚀 Quick Start

### 1. Compile the Application
```bash
cd "Elevator-System Design/src"
javac -d ../out Model/*.java Enum/*.java Service/*.java ElevatorSimulationDemo.java
```

### 2. Run the Application
```bash
java -cp ../out ElevatorSimulationDemo
```

### 3. Use the System

You'll see the initialization screen:
```
═══════════════════════════════════════════════════════════
🏢 ELEVATOR SYSTEM SIMULATION - Distance + Direction Based
═══════════════════════════════════════════════════════════

🏢 [INIT] Elevator 1 initialized at floor 0
🏢 [INIT] Elevator 2 initialized at floor 0
🏢 [INIT] Elevator 3 initialized at floor 0

📍 Initial State:
   Elevator 1: Floor 0, Direction IDLE
   Elevator 2: Floor 5, Direction IDLE
   Elevator 3: Floor 8, Direction IDLE

╔═══════════════════════════════════════════════════════════╗
║           INTERACTIVE ELEVATOR CONTROL                    ║
╚═══════════════════════════════════════════════════════════╝

Commands:
  • Enter floor number (0-20) to request elevator
  • Type 'status' to see all elevator states
  • Type 'exit' or 'quit' to shutdown system

🎮 Enter command (floor number/status/exit): 
```

## 📋 Available Commands

### Request an Elevator
```
🎮 Enter command: 7
```
- System finds the best elevator for floor 7
- Asks for your destination floor
- Assigns the route to the selected elevator

### Check System Status
```
🎮 Enter command: status
```
- Shows all elevators' current floors
- Displays their directions (UP/DOWN/IDLE)
- Lists pending requests in their queues

### Exit the System
```
🎮 Enter command: exit
```
- Gracefully shuts down all elevators
- Closes the application

## 🎮 Example Usage Session

```
🎮 Enter command: 3
📞 NEW REQUEST: Floor 3
[Selection process shows Elevator 2 is selected]
✅ Request assigned to Elevator 2
📍 Enter destination floor: 8
✅ Route added: Floor 3 → Floor 8

🎮 Enter command: status
📊 CURRENT SYSTEM STATUS
🏢 Elevator 1: Floor 0, Direction IDLE, No requests
🏢 Elevator 2: Floor 5, Direction UP, Pending: [3, 8]
🏢 Elevator 3: Floor 8, Direction IDLE, No requests

[Wait a few seconds for processing...]

🎮 Enter command: 10
📞 NEW REQUEST: Floor 10
[System evaluates and selects Elevator 3]
✅ Request assigned to Elevator 3
📍 Enter destination floor: 1
✅ Route added: Floor 10 → Floor 1

🎮 Enter command: exit
🛑 Shutting down elevator system...
✅ System shutdown complete. Goodbye!
```

## 🔥 Key Features

### 1. **Background Processing**
- Elevators operate independently in separate threads
- Check for new requests every 2 seconds
- Process requests automatically without blocking user input

### 2. **Smart Elevator Selection**
The system uses a distance + direction algorithm:
- **Priority 1**: Elevators already moving towards your floor
- **Priority 2**: Idle elevators (closest first)
- **Priority 3**: Elevators moving away (with penalty)

### 3. **Real-time Movement Simulation**
- See elevators pass each floor (0.5s per floor)
- Door operations are simulated (1s)
- Visual indicators (⬆️/⬇️) show direction

### 4. **Comprehensive Logging**
Every action is logged with emoji indicators:
- 🏢 System initialization
- 📞 New requests
- 🔍 Selection process
- 🚀 Movement
- 🛑 Arrivals
- 🚪 Door operations

## 💡 Tips

1. **Multiple Requests**: You can keep entering floor numbers - all elevators work simultaneously!
2. **Check Status**: Use `status` command anytime to see what's happening
3. **Watch the Logs**: Detailed logging shows exactly how decisions are made
4. **Test Edge Cases**: Try requesting floors far apart to see the algorithm in action

## 🐛 Troubleshooting

### Issue: "Command not found" error
**Solution**: Make sure you're in the `src` directory when compiling

### Issue: Elevators seem stuck
**Solution**: Type `status` to check if they have requests. They process every 2 seconds.

### Issue: Can't see all logs
**Solution**: Your terminal may be scrolling. Consider redirecting output:
```bash
java -cp ../out ElevatorSimulationDemo | tee elevator.log
```

## 🎓 Learning Points

This implementation demonstrates:
- **Multi-threading**: Background elevator processing
- **Scheduling Algorithms**: Distance + direction based selection
- **Priority Queues**: Separate UP/DOWN request management
- **State Management**: Tracking elevator positions and directions
- **User Interaction**: Real-time command processing

Enjoy exploring the elevator system! 🎉

