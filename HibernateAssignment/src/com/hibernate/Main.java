package com.hibernate;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class Main {

	private static Component j;

	public static void main(String[] args) throws Exception
	{
		BankRepository br = new BankRepository();
		Scanner sc = new Scanner(System.in);
		int id;
		Patron p = null;
		boolean flag = false;
		while(flag == false) 
		{ 
			printMenu();
			int choice = sc.nextInt();
			switch(choice) 
			{
			/*
			 * 1 - add patron 					-- DONE
			 * 2 - update patron				-- DONE
			 * 3 - delete patron				-- DONE
			 * 4 - search patron by id 			-- DONE
			 * 5 - search patron by name		-- DONE
			 * 6 - add transaction record		-- DONE
			 * 7 - find transaction by id
			 * 8 - add bank 					-- DONE
			 * 9 - remove bank					-- DONE
			 * 10 - update bank					-- DONE
			 * 11 - find bank by id				-- DONE
			 * 12 - find bank by name			-- DONE
			 * 13 - add account 				-- DONE			
			 * 14 - update account				-- DONE
			 * 15 - delete account				-- DONE
			 * 16 - find account details		-- DONE
			 */		
				case 1:
					System.out.println("Enter Patron's id: ");
					id = sc.nextInt();
					System.out.println("Enter Patron's name: ");
					String name = sc.next();
					byte[] img = null;
					JFileChooser fileChooser = new JFileChooser();
			        int result = fileChooser.showOpenDialog(Main.j);
			        if(result == JFileChooser.APPROVE_OPTION) 
			        {
			        	try 
			        	{
			        		File file = fileChooser.getSelectedFile();
			                BufferedImage image = ImageIO.read(file);

			                //convert image to byte[]
			                ByteArrayOutputStream output = new ByteArrayOutputStream();
			                ImageIO.write(image , "png" , output);
			                img = output.toByteArray();
			                p = new Patron(id,name,img);
			                System.out.println(br.add(p));
			                
			        	}catch(Exception e)
			            {
			                e.printStackTrace();
			                img = null;
			            }
			        }

					break;
				case 2:
					System.out.println("Enter Patron's id to be updated: ");
					id = sc.nextInt();
					System.out.println("Enter Patron's new name: ");
					String new_name = sc.next();
					
					byte[] imag = null;
					System.out.println("Enter Patron's new image: ");
					JFileChooser fileChooser1 = new JFileChooser();
			        int result1 = fileChooser1.showOpenDialog(Main.j);
			        if(result1 == JFileChooser.APPROVE_OPTION) 
			        {
			        	try 
			        	{
			        		File file = fileChooser1.getSelectedFile();
			                BufferedImage image = ImageIO.read(file);

			                //convert image to byte[]
			                ByteArrayOutputStream output = new ByteArrayOutputStream();
			                ImageIO.write(image , "png" , output);
			                imag = output.toByteArray();
			                p = new Patron(id,new_name,imag);
			        	}catch(Exception e)
			            {
			                e.printStackTrace();
			                imag = null;
			            }
					
			        }
					
					//p = new Patron(id,new_name,imag);
					System.out.println(br.update(p));
					break;
					
				case 3:
					System.out.println("Enter Patron's id to be deleted: ");
					id = sc.nextInt();
					p = new Patron(id,null,null); 
					System.out.println(br.delete(p));
					break;
					
				case 4:
					
					System.out.println("Enter Patron's id to search: ");
					id = sc.nextInt();
					Patron pr = br.findPatron(id);
					if(pr != null)
						System.out.println(pr.getId()+" "+pr.getName()+" "+pr.getImage());
					else
						System.out.println("Not found");
					System.out.println("-------------------------------------------------");
					break;
					
					
				case 5:
					
					System.out.println("Enter Patron's name to search: ");
					name = sc.next();
					List<Patron> pList = br.findPatron(name);
					System.out.println("Found "+pList.size()+" result(s): ");
					for(Patron patron : pList) 
					{
						System.out.println(patron.getId()+" "+patron.getName()+" "+patron.getImage());
					}
					System.out.println("-------------------------------------------------");
					break;
					
				case 6:
					System.out.println("Enter Transaction id: ");
					id = sc.nextInt();
					System.out.println("Enter Account id: ");
					int acc_id = sc.nextInt();
					Account account = br.findAccount(acc_id);
					System.out.println("Enter Amount: ");
					double amount = sc.nextDouble();
					System.out.println("Enter type: 1.Debit 2.Credit");
					int type = sc.nextInt();
					Transaction t = null;
					if(type == 1)
						t = new Transaction(id, account, amount,AccountType.DEBIT);
					if(type == 2)
						t = new Transaction(id, account, amount,AccountType.CREDIT);
					
					
					System.out.println(br.transact(t));
					break;
				case 7:
					
					System.out.println("Enter Transaction's id to search: ");
					id = sc.nextInt();
					Transaction tr = br.findTransaction(id); 
					System.out.println(tr.getId()+" "+tr.getAccount().getId()+" "+tr.getAccount().getPatron().toString()+" "+tr.getAccount().getBank().toString()+" "+tr.getAmount()+" "+tr.getAccountType().name());
					System.out.println("-------------------------------------------------");
					break;
					
				case 8:
					System.out.println("Enter Bank id: ");
					id = sc.nextInt();
					System.out.println("Enter Bank name: ");
					name = sc.next();
					
					Bank bank = new Bank(id,name);
					System.out.println(br.add(bank));
					break;
				case 9:
					System.out.println("Enter Bank's id to be deleted: ");
					id = sc.nextInt();
					bank = new Bank(id,null);
					System.out.println(br.remove(bank));
					break;
				case 10:
					System.out.println("Enter Bank's id to be updated: ");
					id = sc.nextInt();
					System.out.println("Enter Bank's new name: ");
					new_name = sc.next();
					bank = new Bank(id,new_name);
					System.out.println(br.update(bank));
					break;
					
				case 11:
					
					System.out.println("Enter Bank's id to search: ");
					id = sc.nextInt();
					bank = br.findBank(id); 
					System.out.println(bank.getId()+" "+bank.getName());
					System.out.println("-------------------------------------------------");
					break;
				
				case 12:
					
					System.out.println("Enter Bank's name to search: ");
					name = sc.next();
					List<Bank> bList = br.findBank(name);
					System.out.println("Found "+bList.size()+" result(s): ");
					for(Bank b : bList) 
					{
						System.out.println(b.getId()+" "+b.getName());
					}
					System.out.println("-------------------------------------------------");
					break;
					
				case 13:
					System.out.println("Enter Account id: ");
					id = sc.nextInt();
					System.out.println("Enter Account's Bank Id: ");
					acc_id = sc.nextInt();
					bank = br.findBank(acc_id);
					System.out.println("Enter Account's Patron Id: ");
					int p_id = sc.nextInt();
					p = br.findPatron(p_id);
					
					account = new Account(id,bank,p);
					System.out.println(br.add(account));
					break;
					
				case 14:
					System.out.println("Enter Account's id to be updated: ");
					id = sc.nextInt();
					System.out.println("Enter Bank's new id: ");
					int new_b_id = sc.nextInt();
					bank = br.findBank(new_b_id);
					System.out.println("Enter Patron's new id: ");
					int new_p_id = sc.nextInt();
					p = br.findPatron(new_p_id);
					account = new Account(id,bank,p);
					System.out.println(br.update(account));
					break;
				case 15:
					System.out.println("Enter Account's id to be deleted: ");
					id = sc.nextInt();
					account= new Account(id,null,null);
					System.out.println(br.delete(account));
					break;
					
				case 16:
					
					System.out.println("Enter Account's id to search: ");
					id = sc.nextInt();
					account = br.findAccount(id); 
					System.out.println(account.getId()+" "+account.getBank().toString()+" "+account.getPatron().toString());
					System.out.println("-------------------------------------------------");
					break;
				
				case 0:
					flag = true;
					break;
			}
		}
		sc.close();
	}
	
	public static void printMenu() 
	{
		System.out.println("Choose from the below optoins: ");
		System.out.println("1.Add Patron\n2.Update Patron\n3.Delete Patron\n4.Search Patron by id\n5.Search Patron by name\n6.Add Transaction record\n7.Find Transaction by Id\n8.Add Bank\n9.Remove Bank\n10.Update Bank\n11.Find bank by id\n12.Find bank by name\n13.Add Account\n14.Update Account\n15.Delete Account record\n16.Find Account Details\n0.Exit");
	}

}
