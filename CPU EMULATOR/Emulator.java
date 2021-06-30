import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Humeyra Koseoglu
 * 20180808015
 * @since 07.06.2021
 */

//I used 256 int instead of 256 byte memory. Because when I write code as 256 bytes, overflow occurs.

class CommandOperations {
    static Emulator object = new Emulator();
    static int [] M=new int[256];
    static int AC=0;
    static int flags=0;

    // Starts the program execution
    public static void START() {
        object.programCounter++;
    }

    // Display the value in AC on screen
    public static void DISP() {
        System.out.print(AC+" ");
        object.programCounter++;
    }

    // Stop execution
    public static void HALT() {
        object.programCounter++;
        System.exit(0);
    }

    // Load immediate value / Load immediate value XtoAC
    public static void LOAD(int X) {
        AC=X;
        object.programCounter++;
    }

    // Store a value / Store value in AC to memory location M [X]
    public static void STORE(int X) {
        M[X]=AC;
        object.programCounter++;
    }

    /* Compare
    If the value in AC is greater than value in M [X] then set F flag to 1
    If the value in AC is less than value in M [X] then set F flag to -1
    If the value in AC is equal to value in M [X] then set F flag to 0
    X = MBR
     */
    public static void CMPM(int X) {
        flags = Integer.compare(AC, X);
        object.programCounter++;
    }

    // Conditional Jump / Update the PC with X if theF flag value is positive
    public static void CJMP(int X) {
        if(flags >0) {
            object.programCounter=X;
        } else {
            object.programCounter++;
        }
    }

    // Unconditional Jump / Update the PC value with X
    public static void JMP(int X) {
        object.programCounter=X;
    }

    // ImmediateAddition / Add immediate value of X to AC
    public static void ADD(int X) {
        AC=AC+X;
        object.programCounter++;
    }

    // Immediate Susbtraction / SubtractMemory value of M [X] from AC
    public static void SUB(int X) {
        AC=AC-X;
        object.programCounter++;
    }

    // Immediate Multiplication / Multiply AC with immediate value of N
    public static void MUL(int N) {
        AC=AC*N;
        object.programCounter++;
    }

    // Multiplication with memory / Multiply AC with M [N]
    public static void MULM(int N) {
        AC=AC*M[N];
        object.programCounter++;
    }

    /* Load a memory value / Load memory value stored at M [X] to AC
        X = MBR
     */
    public static void LOADM(int X) {
        AC=X;
        object.programCounter++;
    }

    /* Substraction with memory
        X = MBR
     */
    public static void SUBM(int X) {
        AC=AC-X;
        object.programCounter++;
    }

    /* Addition with memory / Add Memory value of M [X] to AC
       X = MBR
     */
    public static void ADDM(int X) {
        AC=AC+X;
        object.programCounter++;
    }

    public static int[] getM() {
        return M;
    }

    public static void setM(int[] m) {
        M = m;
    }
}


public class Emulator {
    static int programCounter =0;
    static String instruction;
    static int start;
    static int halt;
    static List<Integer> instructionsSequenceNumber=new ArrayList<Integer>();
    static List<String> instructionsCommandsSet=new ArrayList<String>();
    static List<Integer> location_or_value=new ArrayList<Integer>();
    static int [] M=CommandOperations.getM();


    public static void main(String [] args) {
        int i = 0;
        settingList();
        Scanner program_input = null;
        try {
            program_input = new Scanner(new File(args[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }

        while(true) {
            assert program_input != null;
            if (!program_input.hasNext()) break;
            instructionsSequenceNumber.add(program_input.nextInt());
            instruction=program_input.next();
            if(instruction != null && !instruction.equals("START") && !instruction.equals("DISP") && !instruction.equals("HALT")) {
                instructionsCommandsSet.set(instructionsSequenceNumber.get(i), instruction);
                location_or_value.set(instructionsSequenceNumber.get(i),program_input.nextInt());
            } else {
                assert instruction != null;
                if(instruction.equals("START")) {
                    instructionsCommandsSet.set(instructionsSequenceNumber.get(i), instruction);
                    location_or_value.set(instructionsSequenceNumber.get(i),-1);
                    start++;
                } else if(instruction.equals("DISP")) {
                    instructionsCommandsSet.set(instructionsSequenceNumber.get(i), instruction);
                    location_or_value.set(instructionsSequenceNumber.get(i),-1);
                } else {
                    instructionsCommandsSet.set(instructionsSequenceNumber.get(i), instruction);
                    location_or_value.set(instructionsSequenceNumber.get(i),-1);
                    halt++;
                }
            }i++;
        }
        program_input.close();

        while( instructionsCommandsSet.size() > programCounter ) {
            if(instructionsCommandsSet.get(programCounter).equals("START")) {
                CommandOperations.START();
            } else {
                if(start != 0) {
                    if(instructionsCommandsSet.get(programCounter).equals("STORE")) {
                        CommandOperations.STORE(location_or_value.get(programCounter));
                    } else if(instructionsCommandsSet.get(programCounter).equals("LOADM")) {
                        CommandOperations.LOADM(M[location_or_value.get(programCounter)]);
                    } else if(instructionsCommandsSet.get(programCounter).equals("LOAD"))  {
                        CommandOperations.LOAD(location_or_value.get(programCounter));
                    } else if(instructionsCommandsSet.get(programCounter).equals("CMPM")) {
                        CommandOperations.CMPM(M[location_or_value.get(programCounter)]);
                    } else if(instructionsCommandsSet.get(programCounter).equals("CJMP")) {
                        CommandOperations.CJMP(location_or_value.get(programCounter));
                    } else if(instructionsCommandsSet.get(programCounter).equals("JMP")) {
                        CommandOperations.JMP(location_or_value.get(programCounter));
                    } else if(instructionsCommandsSet.get(programCounter).equals("ADD")) {
                        CommandOperations.ADD(location_or_value.get(programCounter));
                    } else if(instructionsCommandsSet.get(programCounter).equals("ADDM")) {
                        CommandOperations.ADDM(M[location_or_value.get(programCounter)]);
                    } else if(instructionsCommandsSet.get(programCounter).equals("SUB")) {
                        CommandOperations.SUB(location_or_value.get(programCounter));
                    } else if(instructionsCommandsSet.get(programCounter).equals("SUBM")) {
                        CommandOperations.SUBM(M[location_or_value.get(programCounter)]);
                    } else if(instructionsCommandsSet.get(programCounter).equals("MUL")) {
                        CommandOperations.MUL(location_or_value.get(programCounter));
                    } else if(instructionsCommandsSet.get(programCounter).equals("MULM")) {
                        CommandOperations.MULM(location_or_value.get(programCounter));
                    } else if(instructionsCommandsSet.get(programCounter).equals("DISP")) {
                        CommandOperations.DISP();
                    } else if(instructionsCommandsSet.get(programCounter).equals("HALT")) {
                        CommandOperations.HALT();
                    } else { programCounter++; }
                } else { programCounter++; }
            }
        }
    }

    public static void settingList() {
        int i=0;
        while(i < 256) {
            location_or_value.add(-100);
            instructionsCommandsSet.add(null);
            i++;
        }
    }

}