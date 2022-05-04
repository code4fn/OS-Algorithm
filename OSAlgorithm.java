import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;
public class OSAlgorithm {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		
		do {
			System.out.println("\n[1] CPU Scheduling Algorithms");
			System.out.println("[2] Banker's Algorithm");
			System.out.println("[3] Page Replacement Algorithms");
			System.out.println("[4] Disk Scheduling Algorithms");
			System.out.println("[5] Exit");
			System.out.print("Enter your choice Algorithm: \n");
			choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				
				int choice1 = 0; 
				
				do {
					System.out.println("\nCPU Scheduling Algorithm:");
					System.out.println("[1] FCFS (First Come First Serve)");
					System.out.println("[2] SJF (Shortest Job First)");
					System.out.println("[3] Priority Queue");
					System.out.println("[4] Round Robin");
					System.out.println("[5] Back");
					System.out.print("Enter your choice: \n");
					choice1 = sc.nextInt();
					
					switch(choice1) {

					// FCFS
					case 1: 
						
						int numberOfProcessed;
						double sumWaitingTime = 0.0;
						double sumTurnAround = 0.0;
						double averageWaitingTime = 0.0;
						double averageTurnAround = 0.0;
						
						System.out.print("Enter the number of process: ");
						numberOfProcessed = sc.nextInt();
						int pid[] = new int[numberOfProcessed];
						int burstTime[] = new int[numberOfProcessed];
						int arrivalTime[] = new int[numberOfProcessed];
						int startingTime[] = new int[numberOfProcessed];
						int completionTime[] = new int[numberOfProcessed];
						int turnAround[] = new int[numberOfProcessed];
						int waitingTime[] = new int[numberOfProcessed];

						for(int i = 0; i < numberOfProcessed; i++) {
							System.out.print("Enter process " + (i+1) + " Arrival Time: ");
							arrivalTime[i] = sc.nextInt();
							System.out.print("Enter process " + (i+1) + " CPU Brust Time (ms): ");
							burstTime[i] = sc.nextInt();
							pid[i] = i+1;
						}

						int temp;

						for(int i = 0; i < numberOfProcessed; i++) {
							for(int j = i+1; j < numberOfProcessed; j++) {
								if(arrivalTime[i] > arrivalTime[j]) {
									temp = arrivalTime[j];
									arrivalTime[i] = arrivalTime[j];
									arrivalTime[j] = temp;

									temp = pid[i];
									pid[i] = pid[j];
									pid[j] = temp;
									temp = burstTime[i];
									burstTime[i] = burstTime[j];
									burstTime[j] = temp;
								}
							}
						}
						System.out.println();
						completionTime[0] = burstTime[0] + arrivalTime[0];
						for(int i = 1; i < numberOfProcessed; i++) {
							completionTime[i] = completionTime[i - 1] + burstTime[i];
						}

						for(int i = 0; i < numberOfProcessed; i++) {
							turnAround[i] = completionTime[i] - arrivalTime[i];
							waitingTime[i] = turnAround[i] - burstTime[i];
						}
						for (int i = 0; i < numberOfProcessed; i++){
							startingTime[i] = completionTime[i] - burstTime[i];
						}
						for (int i = 0; i < waitingTime.length; i++){
							sumWaitingTime += waitingTime[i];
							averageWaitingTime = sumWaitingTime / waitingTime.length;
						}
						for (int i = 0; i < turnAround.length; i++){
							sumTurnAround += turnAround[i];
							averageTurnAround = sumTurnAround / turnAround.length;
						}

						System.out.println("Process\t\tAT\t\tBT(ms)\t\tCT\t\tWT\t\tTAT");
						for(int i = 0; i < numberOfProcessed; i++) {
							System.out.println(pid[i]+"\t\t" + arrivalTime[i] + "\t\t" +burstTime[i] + "\t\t"+ completionTime[i] + "\t\t" +waitingTime[i] + "\t\t" + turnAround[i]);
						}
						System.out.println("Average Waiting Time: " + averageWaitingTime);
						System.out.println("Average Turn Around: " + averageTurnAround);
						break;

					//SJF
					case 2: 
						
						int nestedChoice = 0;
						
						do {
							System.out.println("\n[1] Non-preemptive Priority");
							System.out.println("[2] Preemptive Priority");
							System.out.println("[3] Exit");
							System.out.print("Enter your choice here: \n");
							nestedChoice = sc.nextInt();
							
							switch(nestedChoice) {

							//SJF Non-Preemptive
							case 1: 
								
								System.out.print("Enter no of process: ");
								int sjfNE = sc.nextInt();
								int sjfNEPid[] = new int[sjfNE];
								int sjfNEArrivalTime[] = new int[sjfNE];
								int sjfNEBurstTime[] = new int[sjfNE];
								int sjfNEStartingTime[] = new int[sjfNE];
								int sjfNECompletionTime[] = new int[sjfNE];
								int sjfNETurnAround[] = new int[sjfNE];
								int sjfNEWaitingTime[] = new int[sjfNE];
								int flagNE[] = new int[sjfNE];
								int stNE = 0, totNE = 0;
								double avgwtNE = 0, avgtaNE = 0, sumAvgWTNE = 0, sumAvgTANE = 0;

								for (int i = 0; i < sjfNE; i++) {
									System.out.print("Enter process " + (i + 1) + " Arrival Time: ");
									sjfNEArrivalTime[i] = sc.nextInt();
									System.out.print("Enter process " + (i + 1) + " Burst time: ");
									sjfNEBurstTime[i] = sc.nextInt();
									sjfNEPid[i] = i + 1;
									flagNE[i] = 0;
								}

								boolean aNE = true;

								while (true) {
									int c = sjfNE, min = 999;
									if (totNE == sjfNE)
										break;

									for (int i = 0; i < sjfNE; i++) {
										if ((sjfNEArrivalTime[i] <= stNE) && (flagNE[i] == 0) && (sjfNEBurstTime[i] < min)) {
											min = sjfNEBurstTime[i];
											c = i;
										}
									}
									if (c == sjfNE)
										stNE++;
									else {
										sjfNECompletionTime[c] = stNE + sjfNEBurstTime[c];
										stNE += sjfNEBurstTime[c];
										sjfNETurnAround[c] = sjfNECompletionTime[c] - sjfNEArrivalTime[c];
										sjfNEWaitingTime[c] = sjfNETurnAround[c] - sjfNEBurstTime[c];
										flagNE[c] = 1;
										totNE++;
									}

								}
								for (int i = 0; i < sjfNE; i++) {
									sjfNEStartingTime[i] = sjfNECompletionTime[i] - sjfNEBurstTime[i];
								}
								System.out.println("Process\t\tAT\t\tBT(ms)\t\tCT\t\tWT\t\tTAT");
								for (int i = 0; i < sjfNE; i++) {
									sumAvgWTNE += sjfNEWaitingTime[i];
									avgwtNE = sumAvgWTNE / sjfNEWaitingTime.length;

									sumAvgTANE += sjfNETurnAround[i];
									avgtaNE = sumAvgTANE / sjfNETurnAround.length;

									System.out.println(sjfNEPid[i] + "\t\t" + sjfNEArrivalTime[i] + "\t\t" + sjfNEBurstTime[i] + "\t\t" + sjfNECompletionTime[i] + "\t\t" + sjfNEWaitingTime[i] + "\t\t" + sjfNETurnAround[i]);
								}
								System.out.println("Average Waiting Time: " + avgwtNE);
								System.out.println("Average Turn Around: " + avgtaNE);
								break;

							//Preemptive
							case 2: 
								
								System.out.print("Enter no of process: ");
								int sjfPR = sc.nextInt();
								int sjfPRPid[] = new int[sjfPR];
								int sjfPRArrivalTime[] = new int[sjfPR];
								int sjfPRBurstTime[] = new int[sjfPR];
								int sjfPRStartingTime[] = new int[sjfPR];
								int sjfPRCompletionTime[] = new int[sjfPR];
								int sjfPRTurnAround[] = new int[sjfPR];
								int sjfPRWaitingTime[] = new int[sjfPR];
								int sjfWQT[] = new int[sjfPR];
								int sjfPWT[] = new int [sjfPR];
								int sjfPRFlag[] = new int[sjfPR];
								int k[] = new int[sjfPR];
								int st = 0, tot = 0;
								double avgwtPR = 0, avgtaPR = 0;

								for (int i = 0; i < sjfPR; i++) {
									sjfPRPid[i] = i + 1;
									System.out.print("Enter process " + (i + 1) + " arrival time: ");
									sjfPRArrivalTime[i] = sc.nextInt();
									System.out.print("Enter process " + (i + 1) + " burst time: ");
									sjfPRBurstTime[i] = sc.nextInt();
									k[i] = sjfPRBurstTime[i];
									sjfPRFlag[i] = 0;
								}
								while (true) {
									int min = 99, c = sjfPR;
									if (tot == sjfPR)
										break;
									for (int i = 0; i < sjfPR; i++) {
										if ((sjfPRArrivalTime[i] <= st) && (sjfPRFlag[i] == 0) && (sjfPRBurstTime[i] < min)) {
											min = sjfPRBurstTime[i];
											c = i;
										}
									}
									if (c == sjfPR)
										st++;
									else {
										sjfPRBurstTime[c]--;
										st++;
										if (sjfPRBurstTime[c] == 0) {
											sjfPRCompletionTime[c] = st;
											sjfPRFlag[c] = 1;
											tot++;
										}
									}
								}
								for (int i = 0; i < sjfPR; i++) {
									sjfPRTurnAround[i] = sjfPRCompletionTime[i] - sjfPRArrivalTime[i];
									sjfPRWaitingTime[i] = sjfPRTurnAround[i] - k[i];
									sjfPWT[i] = sjfPRTurnAround[i] - k[i] - sjfPRWaitingTime[i];
									avgwtPR += sjfPRWaitingTime[i];
									avgtaPR += sjfPRTurnAround[i];
								}

								System.out.println("Process\t\tAT\t\tBT(ms)\t\tCT\t\tWT\t\tTAT");
								for (int i = 0; i < sjfPR; i++) {
									System.out.println(sjfPRPid[i] + "\t\t" + sjfPRArrivalTime[i] + "\t\t" + k[i] + "\t\t"+ sjfPRCompletionTime[i] + "\t\t" + sjfPRWaitingTime[i] + "\t\t" + sjfPRTurnAround[i]);
								}
								System.out.println("Average Waiting Time: " + (avgwtPR / sjfPR));
								System.out.println("Average Turn Around Time: " +  (avgtaPR / sjfPR));

								break;

							case 3:
								break;

							default: System.out.println("\nInvalid Choice\n");
							}
						}while(nestedChoice != 3);
						break;

					//Priority
					case 3:
						
						int nestedChoice1 = 0;
						
						do {
							System.out.println("\n[1] Non-preemptive Priority");
							System.out.println("[2] Preemptive Priority");
							System.out.println("[3] Exit");
							System.out.print("Enter your choice here: \n");
							nestedChoice1 = sc.nextInt();
							
							switch(nestedChoice1) {

							//Non-Preemptive
							case 1:

								int nonPrioProcess = 0, CPU = 0, allTime = 0;
								double nonPrioTotal_WT = 0, nonPrioTotal_TAT = 0, nonPrioAVG_WT, nonPrioAVG_TAT;
								System.out.print("Enter the number of Process: ");
								nonPrioProcess = sc.nextInt();

								int NoP = nonPrioProcess;
								int[] atNonPrio = new int[nonPrioProcess];  // Arrival time
								int[] atTNonPrio = new int[nonPrioProcess]; // Arrival time temp.
								int[] btNonPrio = new int[nonPrioProcess];  // Burst time
								int[] ctNonPrio = new int[nonPrioProcess];	// Completion Time
								int[] wtNonPrio = new int[nonPrioProcess];  // Waiting time
								int[] tatNonPrio = new int[nonPrioProcess]; // Turnaround time
								int[] ptNonPrio = new int [nonPrioProcess];   // Priority value
								int[] ptTNonPrio = new int[nonPrioProcess]; // Priority value temp.

								for(int i = 0; i < nonPrioProcess; i++) {
									System.out.print("Enter process " + (i+1) + " Arrival Time: ");
									atNonPrio[i] = sc.nextInt();
									// passting the value of at to at temp.
									atTNonPrio[i] = atNonPrio[i];
									System.out.print("Enter process " + (i+1) + " CPU Brust Time (ms): ");
									btNonPrio[i] = sc.nextInt();
									System.out.print("Enter process " + (i+1) + " Priority Number: ");
									ptNonPrio[i] = sc.nextInt();
									// passing the value of pt to at temp.
									ptTNonPrio[i] = ptNonPrio[i];
								}

								int LAT = 0;    // last arrival time.
								for(int i = 0; i < nonPrioProcess; i++){
									if(atNonPrio[i] > LAT){
										LAT = atNonPrio[i];
									}
								}

								int maxPrio = 0;    // max priority.
								for(int i = 0; i < nonPrioProcess; i++){
									if(ptTNonPrio[i] > maxPrio){
										maxPrio = ptTNonPrio[i];
									}
								}

								int ATi = 0;    // pointing to arrival time index.
								int P1 = ptTNonPrio[0]; // pointing to 1st priority.
								int P2 = ptTNonPrio[0]; // pointing to 2nd priority/

								//finding the First Arrival Time and Highest priority Process
								int j = -1;
								while(NoP > 0 && CPU <= 1000){
									for(int i = 0; i < nonPrioProcess; i++){
										if((atTNonPrio[i] <= CPU) && (atTNonPrio[i] != (LAT+10))){
											if(ptTNonPrio[i] != (maxPrio+1)){
												P2 = ptTNonPrio[i];
												j = 1;

												if(P2 < P1){
													j = 1;
													ATi = i;
													P1 = ptTNonPrio[i];
													P2 = ptTNonPrio[i];
												}
											}
										}
									}

									if(j == -1){
										CPU = CPU + 1;
										continue;
									}

									else{
										wtNonPrio[ATi] = CPU - atTNonPrio[ATi];
										CPU = CPU + btNonPrio[ATi];
										tatNonPrio[ATi] = CPU - atTNonPrio[ATi];
										atTNonPrio[ATi] = LAT + 10;
										j = -1;
										ptTNonPrio[ATi] = maxPrio + 1;
										ATi = 0;        // pointing to arrival index.
										P1 = maxPrio + 1;   // 1st prio
										P2 = maxPrio + 1;   // 2nd prio.
										NoP = NoP - 1;
									}
								}
								for(int i = 0; i < nonPrioProcess; i++){
									ctNonPrio[i] = btNonPrio[i] + atNonPrio[i] + wtNonPrio[i];
								}
								// getting avg.
								for(int i = 0; i < nonPrioProcess; i++){
									nonPrioTotal_WT = nonPrioTotal_WT + wtNonPrio[i];
									nonPrioTotal_TAT = nonPrioTotal_TAT + tatNonPrio[i];
								}

								nonPrioAVG_WT = nonPrioTotal_WT / nonPrioProcess;
								nonPrioAVG_TAT = nonPrioTotal_TAT / nonPrioProcess;

								// print table
								System.out.println("Process\t\tAT\t\tBT(ms)\t\tCT\t\tWT\t\tTAT");
								for(int i = 0; i < nonPrioProcess; i++) {
									System.out.println(i+1+"\t\t" + atNonPrio[i] + "\t\t" +btNonPrio[i] + "\t\t" + ctNonPrio[i] + "\t\t" + wtNonPrio[i]  + "\t\t" +tatNonPrio[i]);
								}
								System.out.println("Average Waiting Time: " +nonPrioAVG_WT);
								System.out.println("Average Turn Around: " +nonPrioAVG_TAT);
								break;

							//Preemptive
							case 2:

                                int[] tempPrio = new int[10];
                                int t, count = 0, short_p;
                                double prioTotal_WT = 0, prioTotal_TAT = 0, prioAVG_WT, prioAVG_TAT;
                                int prioProcess;
                                System.out.print("Enter the number of Process: ");
                                prioProcess = sc.nextInt();

                                int[] prioPID = new int[prioProcess]; // Process ID
                                int[] atPrio = new int[prioProcess];  // Arrival time
                                int[] btPrio = new int[prioProcess];  // Burst time
                                int[] ctPrio = new int[prioProcess];  // Completion Time
                                int[] wtPrio = new int[prioProcess];  // Waiting time
                                int[] tatPrio = new int[prioProcess]; // Turnaround time
                                int[] ptPrio = new int [10];   // Priority value

                                for(int i = 0; i < prioProcess; i++) {
                                    System.out.print("Enter process " + (i+1) + " Arrival Time: ");
                                    atPrio[i] = sc.nextInt();
                                    System.out.print("Enter process " + (i+1) + " CPU Brust Time (ms): ");
                                    btPrio[i] = sc.nextInt();
                                    System.out.print("Enter process " + (i+1) + " Priority Number: ");
                                    ptPrio[i] = sc.nextInt();
                                    // duplicating the values of bt and putting it in temp array.
                                    tempPrio[i] = btPrio[i];
                                }
                                //  initializing bt of a process with max.
                                ptPrio[9] = 100000;

                                for(t = 0; count != prioProcess; t++){
                                    short_p = 9;

                                    for(int i = 0; i < prioProcess; i++){
                                        if(ptPrio[short_p] > ptPrio[i] && atPrio[i] <= t && btPrio[i] > 0){
                                            short_p = i;
                                        }
                                    }

                                    btPrio[short_p] = btPrio[short_p] - 1;

                                    // if any process is completed
                                    if(btPrio[short_p] == 0){
                                        // one process is completed, count increment.
                                        count++;
                                        wtPrio[short_p] = t+1 - atPrio[short_p] - tempPrio[short_p];
                                        tatPrio[short_p] = t+1 - atPrio[short_p];

                                        // total calculation
                                        prioTotal_WT = prioTotal_WT + wtPrio[short_p];
                                        prioTotal_TAT = prioTotal_TAT + tatPrio[short_p];
                                    }
                                }
                                prioAVG_WT = prioTotal_WT / prioProcess;
                                prioAVG_TAT = prioTotal_TAT / prioProcess;
                                for(int i = 0; i < prioProcess; i++){
                                    ctPrio[i] = tempPrio[i] + atPrio[i] + wtPrio[i];
                                }
                                // print table
                                System.out.println("Process\t\tAT\t\tBT(ms)\t\tCT\t\tWT\t\tTAT");
                                for(int i = 0; i < prioProcess; i++) {
                                    System.out.println(i+1+"\t\t" + atPrio[i] + "\t\t" +tempPrio[i] + "\t\t" +ctPrio[i] + "\t\t"+ wtPrio[i]  + "\t\t" + tatPrio[i]);
                                }
                                System.out.println("Average Waiting Time: " +prioAVG_WT);
                                System.out.println("Average Turn Around: " + prioAVG_TAT);

                                break;

							case 3: 
								break;
							default: System.out.println("\nInvalid Choice\n");
							}
						}while(nestedChoice1 != 3);
						break;

					//Round Robin
					case 4:
						
						int rrProcess,time,remain,flag = 0,time_quantum, i;
						int wait_time = 0, turnaround_time = 0;
						double wtAVG_RR, tatAVG_RR;

						System.out.print("Enter the number of Process: ");
						rrProcess = sc.nextInt();

						remain = rrProcess;

						int[] atRR = new int[rrProcess]; // Arrival time
						int[] btRR = new int[rrProcess];  // Burst time
						int[] ctRR = new int[rrProcess];  // Completion Time
						int[] wtRR = new int[rrProcess];  // Waiting Time;
						int[] tatRR = new int[rrProcess]; //Turn Around Time
						int[] rtRR = new int[rrProcess];  // temp

						for(i = 0; i < rrProcess; i++) {
							System.out.print("Enter process " + (i+1) + " Arrival Time: ");
							atRR[i] = sc.nextInt();
							System.out.print("Enter process " + (i+1) + " CPU Brust Time (ms): ");
							btRR[i] = sc.nextInt();
							rtRR[i]=btRR[i];
						}
						System.out.print("Enter Time Quantum / Slice: ");
						time_quantum = sc.nextInt();

						//print table
						System.out.println("Process\t\tAT\t\tBT(ms)\t\tCT\t\tWT\t\tTAT");
						for(time = 0, i = 0; remain != 0;){
							if(rtRR[i] <= time_quantum && rtRR[i] > 0){
								time+=rtRR[i];
								rtRR[i] = 0;
								flag = 1;
							}
							else if(rtRR[i] > 0){
								rtRR[i] -= time_quantum;
								time += time_quantum;
							}
							if(rtRR[i]==0 && flag == 1){
								remain--;
								wtRR[i] = time - atRR[i] - btRR[i];
								tatRR[i] = time - atRR[i];
								ctRR[i] = tatRR[i] + atRR[i];
								System.out.println(i+1 +"\t\t" +atRR[i] +"\t\t" +btRR[i] +"\t\t"+ ctRR[i] +"\t\t" + wtRR[i] +"\t\t" + tatRR[i]);
								wait_time += time - atRR[i] - btRR[i];
								turnaround_time += time - atRR[i];
								flag = 0;
							}

							if(i==rrProcess-1)
								i = 0;
							else if(atRR[i+1] <= time)
								i++;
							else
								i = 0;
						}
						wtAVG_RR = wait_time * 1.0 / rrProcess;
						tatAVG_RR = turnaround_time * 1.0 / rrProcess;
						System.out.println("Average Waiting Time: " + String.format("%.2f", wtAVG_RR));
						System.out.println("Average Turn Around: " + String.format("%.2f", tatAVG_RR));
						break;
						
					case 5: 
						break;
					}
				}while(choice1 != 5);
				break;

			//Banker's Algo
			case 2:

				int i, j;
				int n = 0; // Number of processes
				int m = 0; // Number of resources

				System.out.println("Enter the number of the processes: ");
				n = sc.nextInt();

				System.out.println("Enter the number of resources: ");
				m = sc.nextInt();

				int resourceInstance[] = new int[m];
				int alloc[][] = new int[n][m];
				int max[][] = new int[n][m];
				int avail[] = new int[m];
				int need[][] = new int[n][m];
				int safeSequence[] = new int[n];
				int sum[] = new int[m];

				System.out.println("Enter the Resources-Instance: ");
				for(i = 0; i < m; i++){
					resourceInstance[i] = sc.nextInt();
				}

				// Allocation Matrix
				System.out.print("\nEnter the Allocation ");
				for(i = 0; i < n; i++){
					System.out.println("\nP" + i);
					for(j = 0; j < m; j++){
						alloc[i][j] = sc.nextInt();
					}
					System.out.println();
				}

				// MAX Matrix
				System.out.print("\nEnter the Max ");
				for(i = 0; i < n; i++){
					System.out.println("\nP" + i);
					for(j = 0; j < m; j++){
						max[i][j] = sc.nextInt();
					}
					System.out.println();
				}

				System.out.println("Allocation");
				for(i = 0; i < n; i++){
					for(j = 0; j < m; j++){
						System.out.print(alloc[i][j] + " ");
					}
					System.out.println();
				}

				System.out.println("\nMax");
				for(i = 0; i < n; i++){
					for(j = 0; j < m; j++){
						System.out.print(max[i][j] + " ");
					}
					System.out.println();
				}

				//For Sum of the Allocation
				int maxLength = 0;
				for (i = 0; i < alloc.length; i++) {
					if (maxLength < alloc[i].length)
						maxLength = alloc[i].length;
				}
				for (i = 0; i < maxLength; i++) {
					for (j = 0; j < alloc.length; j++) {
						if (i < alloc[j].length)
							sum[i] += alloc[j][i];
					}
				}
				// Available Resources
				for (i = 0; i < m; i++) {
					avail[i] = resourceInstance[i] - sum[i];
				}

				//For Need
				for (i = 0; i < n; i++) {
					for (j = 0; j < m; j++) {
						need[i][j] = max[i][j] - alloc[i][j];
					}
				}
				System.out.println("\nNeed");
				for (i = 0; i < n; i++) {
					for (j = 0; j < m; j++) {
						System.out.print(need[i][j] + " ");
					}
					System.out.println();
				}

				int count = 0;

				//visited array to find the already allocated process
				boolean visited[] = new boolean[n];
				for (i = 0; i < n; i++) {
					visited[i] = false;
				}
				//work array to store the copy of available resources
				int work[] = new int[m];
				for (i = 0; i < m; i++) {
					work[i] = avail[i];
				}
				while (count < n) {
					boolean flag = false;
					for (i = 0; i < n; i++) {
						if (visited[i] == false) {
							for (j = 0; j < m; j++) {
								if (need[i][j] > work[j])
									break;
							}
							if (j == m) {
								safeSequence[count++] = i;
								visited[i] = true;
								flag = true;

								for (j = 0; j < m; j++) {
									work[j] = work[j] + alloc[i][j];
								}
							}
						}
					}
					if (flag == false) {
						break;
					}
				}
				if (count < n) {
					System.out.println("\nThe System is UnSafe!");
				} else {
					//System.out.println("The given System is Safe");
					System.out.println("\nFollowing is the SAFE Sequence");
					for (i = 0; i < n; i++) {
						System.out.print("P" + safeSequence[i]);
						if (i != n - 1)
							System.out.print(" -> ");
					}
					System.out.println("\n");
				}
				break;

			//Page Replacement Algo
			case 3:

				int choice2 = 0;

				do {
					System.out.println("\nPage Replacement Algorithms! ");
					System.out.println("[1] First-In-First-Out");
					System.out.println("[2] Optimal Algorithm");
					System.out.println("[3] Least Recently Used");
					System.out.println("[4] Most Recently Used");
					System.out.println("[5] Least Frequently Used");
					System.out.println("[6] Most Frequently Used");
					System.out.println("[7] Back");
					System.out.print("Enter your choice here: ");
					choice2 = sc.nextInt();

					switch(choice2) {

					//FIFO
					case 1:

						int fifoPage, fifoFrame, k1, avail1, count1 = 0;
						int frame[] = new int[10];

						System.out.print("Enter the Number of Frames: ");
						fifoFrame = sc.nextInt();

						System.out.print("Enter the Length of Reference String: ");
						fifoPage = sc.nextInt();

						int fifoRefString[] = new int[fifoPage];

						System.out.print("Enter the Reference String: ");
						for (int i1 = 0; i1 < fifoPage; i1++) {
							fifoRefString[i1] = sc.nextInt();
						}


						for (int i1 = 0; i1 < fifoFrame; i1++)
							frame[i1] = -1;
						int j1 = 0;

						for (int i1 = 0; i1 < fifoPage; i1++) {
							avail1 = 0;
							for (k1 = 0; k1 < fifoFrame; k1++)
								if (frame[k1] == fifoRefString[i1])
									avail1 = 1;
							if (avail1 == 0) {
								frame[j1] = fifoRefString[i1];
								j1 = (j1 + 1) % fifoFrame;
								count1++;
							}
						}
						System.out.println("\nPage Fault: " + count1 + "\n");
						break;

					//Optimal
					case 2:

						int optFrames;
						int optPointer = 0;
						int numFault = 0;
						int optPage;
						boolean isFull = false;

						System.out.print("Enter the Number of Frames: ");
						optFrames = sc.nextInt();

						System.out.print("Enter the Length of Reference String: ");
						optPage = sc.nextInt();

						int[] optRefString = new int[optPage];
						int[][] mem_layout = new int[optPage][optFrames];
						int[] buffer = new int[optFrames];
						int[] fault = new int[optPage];

						for(int optimalJ = 0; optimalJ < optFrames; optimalJ++) {
							buffer[optimalJ] = -1;
						}

						System.out.print("Enter the Reference String: ");
						for(int i2 = 0; i2 < optPage; i2++) {
							optRefString[i2] = sc.nextInt();
						}

						for(int i2 = 0; i2 < optPage; i2++) {
							int search = -1;
							for(int optimalJ = 0; optimalJ < optFrames; optimalJ++) {
								if(buffer[optimalJ] == optRefString[i2]) {
									search = optimalJ;
									fault[i2] = numFault;
									break;
								}
							}

							if(search == -1) {
								if(isFull) {
									int index[] = new int[optFrames];
									boolean index_flag[] = new boolean[optFrames];
									for(int optimalJ = i2 + 1; optimalJ < optPage; optimalJ++) {
										for(int optimalK = 0; optimalK < optFrames; optimalK++) {
											if((optRefString[optimalJ] == buffer[optimalK]) && (index_flag[optimalK] == false)) {
												index[optimalK] = optimalJ;
												index_flag[optimalK] = true;
												break;
											}
										}
									}
									int max1 = index[0];
									optPointer = 0;
									if(max1 == 0) {
										max1 = 200;
									}

									for(int optimalJ = 0; optimalJ < optFrames; optimalJ++) {
										if(index[optimalJ] == 0) {
											index[optimalJ] = 200;
										}

										if(index[optimalJ] > max1) {
											max1 = index[optimalJ];
											optPointer = optimalJ;
										}
									}
								}
								buffer[optPointer] = optRefString[i2];
								numFault++;
								fault[i2] = numFault;
								if(!isFull) {
									optPointer++;
									if(optPointer == optFrames) {
										optPointer = 0;
										isFull = true;
									}
								}
							}

							for(int optimalJ = 0; optimalJ < optFrames; optimalJ++) {
								mem_layout[i2][optimalJ] = buffer[optimalJ];
							}
						}
						System.out.println("\nPage Fault: " + numFault + "\n");
						break;

					//LRU
					case 3:

						System.out.print("Enter the Number of Frames: ");
						int numberFrame = sc.nextInt();

						System.out.print("Enter the Length of Reference String: ");
						int nr = sc.nextInt();

						int lruFrame[] = new int[nr];
						int page[] = new int[nr];

						System.out.print("Enter the Reference String: ");
						for(int i3 = 0; i3 < nr; i3++)
							page[i3] = sc.nextInt();

						for(int i3 = 0; i3 < numberFrame; i3++)
							lruFrame[i3] = -1;

						int flag,lruPageFault = 0;
						int age[] = new int[numberFrame];

						for(int i3 = 0; i3 < numberFrame; i3++)
							age[i3] = 0;
						for(int lruJ = 0, i3 = 0; lruJ < numberFrame && i3 < nr; lruJ = lruJ % numberFrame, i3++) {
							flag = 0;

							for(int y = 0; y < numberFrame; y++) {
								if(lruFrame[y] == page[i3]) {
									flag = 1;
									age[y] = 0;
								}
							}
							if(flag == 0)
								if(lruFrame[lruJ] == -1) {
									lruFrame[lruJ] = page[i3];
									lruPageFault++;
									lruJ++;
								}
								else {
									int max2 = age[0], loc = 0;
									lruPageFault++;
									for(int b = 0; b < numberFrame; b++)
										if(age[b] > max2) {
											max2 = age[b]; loc = b;
										}
									lruFrame[loc] = page[i3];
									age[loc] = 0;
									lruJ++;
								}
							for(int a = 0; a < numberFrame; a++) {
								if(lruFrame[a] == -1)
									age[a] = 0;
								else
									age[a]++;
							}
						}
						System.out.println("\nPage Fault: " + lruPageFault + "\n");
						break;

					//MRU
					case 4:

						System.out.print("Enter the Number of Frames: ");
						int numberFrameMRU = sc.nextInt();

						System.out.print("Enter the Length of Reference String: ");
						int nr4 = sc.nextInt();

						int mruFrame[] = new int[nr4];
						int pageMRU[] = new int[nr4];

						System.out.print("Enter the Reference String: ");
						for(int i4 = 0; i4 < nr4; i4++)
							pageMRU[i4] = sc.nextInt();

						for(int i4 = 0; i4 < numberFrameMRU; i4++)
							mruFrame[i4] = -1;
						int mruPageFault = 0;
						int ageMRU[] = new int[numberFrameMRU];
						for(int i4 = 0; i4 < numberFrameMRU; i4++)
							ageMRU[i4] = 0;
						for(int mruJ = 0, i4 = 0; mruJ < numberFrameMRU && i4 < nr4; mruJ = mruJ % numberFrameMRU, i4++) {
							flag = 0;
							for(int y = 0; y < numberFrameMRU; y++) {
								if(mruFrame[y] == pageMRU[i4]) {
									flag = 1;
									ageMRU[y] = 0;
								}
							}
							if(flag == 0)
								if(mruFrame[mruJ] == -1) {
									mruFrame[mruJ] = pageMRU[i4];
									mruPageFault++;
									mruJ++;
								}
								else {
									int max3 = ageMRU[0], loc = 0;
									mruPageFault++;
									for(int b = 0; b < numberFrameMRU; b++)
										if(ageMRU[b] < max3) {
											max3 = ageMRU[b]; loc = b;
										}
									mruFrame[loc] = pageMRU[i4];
									ageMRU[loc] = 0;
									mruJ++;
								}
							for(int a = 0; a < numberFrameMRU; a++) {
								if(mruFrame[a] == -1)
									ageMRU[a] = 0;
								else
									ageMRU[a]++;
							}
						}
						System.out.println("\nPage Fault: " + mruPageFault + "\n");
						break;

					//LFU
					case 5:
						int fLFU, pLFU;
						int hit = 0, pageFault;
						int pages[] = new int[50];
						int frameLFU[] = new int[50];
						int countLFU[] = new int[50];
						int time[] = new int[50];
						int least, minTime, temp = -1;

						System.out.print("Enter the Number of Frames: ");
						fLFU = sc.nextInt();

						System.out.print("Enter the Length of Reference String: ");
						pLFU = sc.nextInt();

						System.out.print("Enter the Reference String: ");
						for(i = 0; i < pLFU; i++){
							pages[i] = sc.nextInt();
						}

						for(i = 0; i < fLFU; i++){
							frameLFU[i] =- 1;
						}
						for(i = 0; i < 50; i++){
							countLFU[i] = 0;
						}
						for(i = 0; i < pLFU; i++) {
							countLFU[pages[i]]++;
							time[pages[i]] = i;
							flag = 1;
							least = frameLFU[0];
							for (j = 0; j < fLFU; j++) {
								if (frameLFU[j] == -1 || frameLFU[j] == pages[i]) {
									if (frameLFU[j] != -1) {
										hit++;
									}

									flag = 0;

									frameLFU[j] = pages[i];
									break;
								}

								if (countLFU[least] > countLFU[frameLFU[j]]) {
									least = frameLFU[j];
								}
							}

							if (flag == 1) {
								minTime = 50;
								for (j = 0; j < fLFU; j++) {
									if (countLFU[frameLFU[j]] == countLFU[least] && time[frameLFU[j]] < minTime) {
										temp = j;
										minTime = time[frameLFU[j]];
									}
								}
								countLFU[frameLFU[temp]] = 0;
								frameLFU[temp] = pages[i];
							}
						}
						pageFault = pLFU - hit;
						System.out.println("\nPage Faults: " +pageFault + "\n");
						break;

					//MFU
					case 6:

						int mfuFault = 0, mfuI, mfuJ, noPages, noFrames;
						int mfuFlag;
						int flagFound;
						int mfuCount = 0;

						System.out.print("Enter number of Frames: ");
						noFrames = sc.nextInt();

						int frameAge[] = new int[noFrames];

						System.out.print("Enter the length of the Reference String: ");
						noPages = sc.nextInt();

						int frameFREQ[] = new int[noPages];
						int mfuPages[] = new int[noPages];

						System.out.print("Enter the Reference String: ");
						for(mfuI = 0; mfuI < noPages; mfuI++ ){
							 mfuPages[mfuI] = sc.nextInt();
						}

						int mfuFrames[] = new int[noFrames];

						for(mfuI = 0; mfuI < noFrames; mfuI++){
							mfuFrames[mfuI] =- 1;
							frameAge[mfuI] =- 1;
						}

						for(mfuJ = 0; mfuJ < noPages; mfuJ++){
							frameFREQ[mfuJ] = 0;
						}

						for(mfuJ = 0; mfuJ < noPages; mfuJ++){
							int index = 0;
							flagFound = 0;
							mfuFlag = 0;

							for(mfuI = 0; mfuI < noFrames; mfuI++){
								if(mfuFrames[mfuI] == mfuPages[mfuJ]){
									flagFound = 1;
									mfuFlag = 1;
									index = 1;
									break;
								}
							}
							if(flagFound == 0){
								for(mfuI = 0; mfuI < noFrames; mfuI++){
									if(mfuFrames[mfuI] == -1){
										mfuFrames[mfuI] = mfuPages[mfuJ];
										mfuFlag = 1;
										mfuCount++;
										frameAge[mfuI] = mfuCount;
										mfuFault++;
										frameFREQ[mfuI] = 1;
										break;
									}
								}
								if(mfuFlag == 0){
									int bestmfu = 0;
									for(mfuI = 0; mfuI < noFrames; mfuI++){
										if(frameFREQ[mfuI] > frameFREQ[bestmfu])
											bestmfu = mfuI;
									}
									mfuFrames[bestmfu] = mfuPages[mfuJ];
									mfuFault++;
									frameFREQ[bestmfu] = 1;
								}
							}
							else{
								frameFREQ[index]++;
							}
						}
						System.out.println("\nPage Faults: " + mfuFault +"\n");
						break;

					case 7: break;
					default: System.out.println("\nInvalid Choice\n");

					}
				}while(choice2 != 7);
				break;

			//Disk Scheduling Algo
			case 4:
				
				int choice3 = 0;
				
				do {
					System.out.println("Disk Scheduling Algorithm");
					System.out.println("[1] FCFS (First Come First Serve)");
					System.out.println("[2] SSTF (Shortest Seek Time First)");
					System.out.println("[3] SCAN Disk Scheduling");
					System.out.println("[4] CSCAN Disk Scheduling");
					System.out.println("[5] LOOK Disk Scheduling");
					System.out.println("[6] CLOOK Disk Scheduling");
					System.out.println("[7] Back");
					System.out.print("\nEnter your choice here: ");
					choice3 = sc.nextInt();
					
					switch(choice3) {

					//FCFS
					case 1: 
						
						int numberFCFS, seekTimeFCFS = 0, headFCFS;

						// Getting the values from the user. 
						System.out.print("Enter the number of requests: ");
						numberFCFS = sc.nextInt();

						int[] queuesFCFS = new int[numberFCFS];

						System.out.print("Enter the Queues: ");
						for(i = 0; i < numberFCFS; i++) {
							queuesFCFS[i] = sc.nextInt();
						}

						System.out.print("Enter the initial head position: ");
						headFCFS = sc.nextInt();
						
						// Calculating the seek time for FCFS.
						for(i = 0; i < numberFCFS; i++) {
							seekTimeFCFS = seekTimeFCFS + Math.abs(queuesFCFS[i] - headFCFS);
							headFCFS = queuesFCFS[i];
						}
						
						// Display result
						System.out.println("\nTotal Head Movement: " +seekTimeFCFS +" cylinders\n");
						break;

					//SSTF
					case 2: 
						
						int numberSSTF, seekTimeSSTF = 0, headSSTF, countSSTF = 0;

						// Getting the values from the user. 
						System.out.print("Enter the number of requests: ");
						numberSSTF = sc.nextInt();

						int[] queuesSSTF = new int[numberSSTF];

						System.out.print("Enter the Queues: ");
						for(i = 0; i < numberSSTF; i++) {
							queuesSSTF[i] = sc.nextInt();
						}

						System.out.print("Enter the initial head position: ");
						headSSTF = sc.nextInt();
						
						// Calculating the seek time for FCFS.
						while(countSSTF != numberSSTF) {
							int minSSTF = 9999, d, index = 0;
							for(i = 0; i < numberSSTF; i++) {
								d = Math.abs(queuesSSTF[i]-headSSTF);
								if(minSSTF > d) {
									minSSTF = d;
									index = i;
								}
							}
							seekTimeSSTF += minSSTF;
							headSSTF = queuesSSTF[index];
							queuesSSTF[index] = 9999;
							countSSTF++;
						}
						
						// Display result
						System.out.println("\nTotal Head Movement: " +seekTimeSSTF +" cylinders\n");
						break;

					//SCAN
					case 3: 
						
						int scanDiskSize;
						int numberSCAN;
						int scanHead;
						int scanDirection;
						int scanSeekCount = 0;
						int scanDistance, scanCurTrack;

						System.out.println("Enter Disk Size: ");
						scanDiskSize = sc.nextInt();

						System.out.println("Enter the number of request: ");
						numberSCAN = sc.nextInt();

						int scanArray[] = new int[numberSCAN];

						System.out.println("Enter the Queues: ");
						for(i = 0; i < numberSCAN; i++){
							scanArray[i] = sc.nextInt();
						}

						System.out.println("Enter the initial head position: ");
						scanHead = sc.nextInt();

						System.out.println("Enter what direction [0]Left and [1]Right: ");
						scanDirection = sc.nextInt();

						Vector<Integer> scanLeft = new Vector<Integer>(), scanRight = new Vector<Integer>();
						Vector<Integer> seekSequence = new Vector<Integer>();

						// appending end values
						// which has to be visited
						// before reversing the direction
						if (scanDirection == 0)
							scanLeft.add(0);
						else if
							(scanDirection == 1)
							scanRight.add(scanDiskSize - 1);

						for (i = 0; i < numberSCAN; i++) {
							if (scanArray[i] < scanHead)
								scanLeft.add(scanArray[i]);
							if (scanArray[i] > scanHead)
								scanRight.add(scanArray[i]);
						}

						// sorting left and right vectors
						Collections.sort(scanLeft);
						Collections.sort(scanRight);

						// run the while loop two times.
						// one by one scanning right
						// and left of the head
						int run = 2;
						while (run-- >0) {
							if (scanDirection == 0) {
								for (i = scanLeft.size() - 1; i >= 0; i--) {
									scanCurTrack = scanLeft.get(i);

									// appending current track to seek sequence
									seekSequence.add(scanCurTrack);

									// calculate absolute distance
									scanDistance = Math.abs(scanCurTrack - scanHead);

									// increase the total count
									scanSeekCount += scanDistance;

									// accessed track is now the new head
									scanHead = scanCurTrack;
								}
								scanDirection = 1;
							}
							else if (scanDirection == 1) {
								for (i = 0; i < scanRight.size(); i++) {
									scanCurTrack = scanRight.get(i);

									// appending current track to seek sequence
									seekSequence.add(scanCurTrack);

									// calculate absolute distance
									scanDistance = Math.abs(scanCurTrack - scanHead);

									// increase the total count
									scanSeekCount += scanDistance;

									// accessed track is now new head
									scanHead = scanCurTrack;
								}
								scanDirection = 0;
							}
						}
						System.out.println("\nTotal Head Movement: " + scanSeekCount +" cylinders\n");
						break;

					//CSCAN
					case 4: 
						
						int numberCSCAN = 0;
						int cscanDiskSize = 0;
						int cscanHead = 0;
						int cscanSeekCount = 0;
						int cscanDirection = 0;
						int csanDistance, cscanCurTrack;

						System.out.println("Enter Disk Size: ");
						cscanDiskSize = sc.nextInt();

						System.out.println("Enter the number request: ");
						numberCSCAN = sc.nextInt();

						int cscanArray[] = new int[numberCSCAN];

						System.out.println("Enter the Queues: ");
						for(i = 0; i < numberCSCAN; i++){
							cscanArray[i] = sc.nextInt();
						}

						System.out.println("Enter Initial Head Position: ");
						cscanHead = sc.nextInt();

						System.out.println("Enter what direction [0]Left and [1]Right: ");
						cscanDirection = sc.nextInt();

						Vector<Integer> csanLeft = new Vector<Integer>();
						Vector<Integer> csanRight = new Vector<Integer>();
						Vector<Integer> cscanSeekSequence = new Vector<Integer>();

						// Appending end values which has
						// to be visited before reversing
						// the direction

						if (cscanDirection == 0)
							csanLeft.add(0);
						else if
						(cscanDirection == 1)
							csanRight.add(cscanDiskSize - 1);

						for (i = 0; i < numberCSCAN; i++) {
							if (cscanArray[i] < cscanHead)
								csanLeft.add(cscanArray[i]);
							if (cscanArray[i] > cscanHead)
								csanRight.add(cscanArray[i]);
						}

						// Sorting left and right vectors
						Collections.sort(csanLeft);
						Collections.sort(csanRight);

						// First service the requests
						// on the right side of the
						// head.

						if(cscanDirection == 1) {
							for (i = 0; i < csanRight.size(); i++) {
								cscanCurTrack = csanRight.get(i);

								// Appending current track to seek sequence
								cscanSeekSequence.add(cscanCurTrack);

								// Calculate absolute distance
								csanDistance = Math.abs(cscanCurTrack - cscanHead);

								// Increase the total count
								cscanSeekCount += csanDistance;

								// Accessed track is now new head
								cscanHead = cscanCurTrack;
							}
							// Once reached the right end
							// jump to the beggining.
							cscanHead = 0;
							// adding seek count for head returning from 199 to
							// 0
							cscanSeekCount += (cscanDiskSize - 1);

							// Now service the requests again
							// which are left.
							for (i = 0; i < csanLeft.size(); i++) {
								cscanCurTrack = csanLeft.get(i);

								// Appending current track to
								// seek sequence
								cscanSeekSequence.add(cscanCurTrack);

								// Calculate absolute distance
								csanDistance = Math.abs(cscanCurTrack - cscanHead);

								// Increase the total count
								cscanSeekCount += csanDistance;

								// Accessed track is now the new head
								cscanHead = cscanCurTrack;
							}
						}
						else if(cscanDirection == 0) {
							for (i = csanLeft.size() - 1; i >= 0; i--) {
								cscanCurTrack = csanLeft.get(i);

								// Appending current track to
								// seek sequence
								cscanSeekSequence.add(cscanCurTrack);

								// Calculate absolute distance
								csanDistance = Math.abs(cscanCurTrack - cscanHead);

								// Increase the total count
								cscanSeekCount += csanDistance;

								// Accessed track is now the new head
								cscanHead = cscanCurTrack;
							}

							// Once reached the right end
							// jump to the beggining.
							cscanHead = 199;
							// adding seek count for head returning from 199 to
							// 0
							cscanSeekCount += (cscanDiskSize - 1);

							// Now service the requests again
							// which are left.

							for (i = csanRight.size() - 1; i >= 0; i--) {
								cscanCurTrack = csanRight.get(i);

								// Appending current track to seek sequence
								cscanSeekSequence.add(cscanCurTrack);

								// Calculate absolute distance
								csanDistance = Math.abs(cscanCurTrack - cscanHead);

								// Increase the total count
								cscanSeekCount += csanDistance;

								// Accessed track is now new head
								cscanHead = cscanCurTrack;
							}
						}
						System.out.println("\nTotal Head Movement: " + cscanSeekCount +" cylinders\n");
						break;

					//LOOK
					case 5: 
						
						int lookDiskSize = 0;
						int numberLOOK;
						int lookHead;
						int lookDirection;
						int lookSeekCount = 0;
						int lookDistance, lookCurTrack;

						System.out.println("Enter Disk Size: ");
						lookDiskSize = sc.nextInt();

						System.out.println("Enter the number of request: ");
						numberLOOK = sc.nextInt();

						int lookArray[] = new int[numberLOOK];

						System.out.println("Enter the Queues: ");
						for(i = 0; i < numberLOOK; i++){
							lookArray[i] = sc.nextInt();
						}

						System.out.println("Enter the initial head position: ");
						lookHead = sc.nextInt();

						System.out.println("Enter what direction [0]Left and [1]Right: ");
						lookDirection = sc.nextInt();

						Vector<Integer> lookLeft = new Vector<Integer>(), lookRight = new Vector<Integer>();
						Vector<Integer> lookSequence = new Vector<Integer>();


						for (i = 0; i < numberLOOK; i++) {
							if (lookArray[i] < lookHead)
								lookLeft.add(lookArray[i]);
							if (lookArray[i] > lookHead)
								lookRight.add(lookArray[i]);
						}

						// sorting left and right vectors
						Collections.sort(lookLeft);
						Collections.sort(lookRight);

						// run the while loop two times.
						// one by one scanning right
						// and left of the head
						int runLook = 2;
						while (runLook-- >0) {
							if (lookDirection == 0) {
								for (i = lookLeft.size() - 1; i >= 0; i--) {
									lookCurTrack = lookLeft.get(i);

									// appending current track to seek sequence
									lookSequence.add(lookCurTrack);

									// calculate absolute distance
									lookDistance = Math.abs(lookCurTrack - lookHead);

									// increase the total count
									lookSeekCount += lookDistance;

									// accessed track is now the new head
									lookHead = lookCurTrack;
								}
								lookDirection = 1;
							}
							else if (lookDirection == 1) {
								for (i = 0; i < lookRight.size(); i++) {
									lookCurTrack = lookRight.get(i);

									// appending current track to seek sequence
									lookSequence.add(lookCurTrack);

									// calculate absolute distance
									lookDistance = Math.abs(lookCurTrack - lookHead);

									// increase the total count
									lookSeekCount += lookDistance;

									// accessed track is now new head
									lookHead = lookCurTrack;
								}
								lookDirection = 0;
							}
						}
						System.out.println("\nTotal Head Movement: " + lookSeekCount +" cylinders\n");
						break;

					//CLOOK
					case 6: 
						
						int numberCLOOK;
						int clookDiskSize = 0;
						int clookHead;
						int clookSeekCount = 0;
						int clookDirection;
						int clookDistance, clookCurTrack;

						System.out.println("Enter Disk Size: ");
						clookDiskSize = sc.nextInt();

						System.out.println("Enter the number request: ");
						numberCLOOK = sc.nextInt();

						int clookArray[] = new int[numberCLOOK];

						System.out.println("Enter the Queues: ");
						for(i = 0; i < numberCLOOK; i++){
							clookArray[i] = sc.nextInt();
						}

						System.out.println("Enter Initial Head Position: ");
						clookHead = sc.nextInt();

						System.out.println("Enter what direction [0]Left and [1]Right: ");
						clookDirection = sc.nextInt();

						Vector<Integer> clookLeft = new Vector<Integer>();
						Vector<Integer> clookRight = new Vector<Integer>();
						Vector<Integer> clookSeekSequence = new Vector<Integer>();

						// Appending end values which has
						// to be visited before reversing
						// the direction

						for (i = 0; i < numberCLOOK; i++) {
							if (clookArray[i] < clookHead)
								clookLeft.add(clookArray[i]);
							if (clookArray[i] > clookHead)
								clookRight.add(clookArray[i]);
						}

						// Sorting left and right vectors
						Collections.sort(clookLeft);
						Collections.sort(clookRight);

						// First service the requests
						// on the right side of the
						// head.

						if(clookDirection == 1) {
							for (i = 0; i < clookRight.size(); i++) {
								clookCurTrack = clookRight.get(i);

								// Appending current track to seek sequence
								clookSeekSequence.add(clookCurTrack);

								// Calculate absolute distance
								clookDistance = Math.abs(clookCurTrack - clookHead);

								// Increase the total count
								clookSeekCount += clookDistance;

								// Accessed track is now new head
								clookHead = clookCurTrack;
							}
							// Once reached the right end
							// jump to the beggining.
							clookSeekCount += Math.abs(clookHead - clookLeft.get(0));
							clookHead = clookLeft.get(0);


							// Now service the requests again
							// which are left.
							for (i = 0; i < clookLeft.size(); i++) {
								clookCurTrack = clookLeft.get(i);

								// Appending current track to
								// seek sequence
								clookSeekSequence.add(clookCurTrack);

								// Calculate absolute distance
								clookDistance = Math.abs(clookCurTrack - clookHead);

								// Increase the total count
								clookSeekCount += clookDistance;

								// Accessed track is now the new head
								clookHead = clookCurTrack;
							}
						}
						else if(clookDirection == 0) {
							for (i = clookLeft.size() - 1; i >= 0; i--) {
								clookCurTrack = clookLeft.get(i);

								// Appending current track to
								// seek sequence
								clookSeekSequence.add(clookCurTrack);

								// Calculate absolute distance
								clookDistance = Math.abs(clookCurTrack - clookHead);

								// Increase the total count
								clookSeekCount += clookDistance;

								// Accessed track is now the new head
								clookHead = clookCurTrack;
							}

							// Once reached the right end
							// jump to the beggining.
							clookSeekCount += Math.abs(clookHead - clookRight.get(clookRight.size() - 1));
							clookHead = clookRight.get(clookRight.size() - 1);


							// Now service the requests again
							// which are left.

							for (i = clookRight.size() - 1; i >= 0; i--) {
								clookCurTrack = clookRight.get(i);

								// Appending current track to seek sequence
								clookSeekSequence.add(clookCurTrack);

								// Calculate absolute distance
								clookDistance = Math.abs(clookCurTrack - clookHead);

								// Increase the total count
								clookSeekCount += clookDistance;

								// Accessed track is now new head
								clookHead = clookCurTrack;
							}
						}
						System.out.println("\nTotal Head Movement: " + clookSeekCount +" cylinders\n");
						break;
					case 7:
						break;
					default: System.out.println("\nInvalid Choice!\n");
					}
				}while(choice3 != 7);
				break;
			case 5:
				System.out.println("\nThank you for using the program!");
				break;
			default: System.out.println("\nInvalid Choice!\n");
			}
		}while(choice != 5);
	}
}
