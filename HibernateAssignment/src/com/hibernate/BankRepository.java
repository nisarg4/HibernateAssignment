package com.hibernate;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mysql.cj.jdbc.Blob;

public class BankRepository {

	private Connection con;

	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Patron.class)
			.addAnnotatedClass(Bank.class).addAnnotatedClass(Account.class).addAnnotatedClass(Transaction.class)
			.buildSessionFactory();

	// Add Method
	public Result add(Patron patron) {
		Session session = factory.getCurrentSession();
		try {
			// create student object
			Patron tempStudent = new Patron(patron.getId(), patron.getName(), patron.getImage());

			// start a transaction
			session.beginTransaction();

			// save the patron object
			session.save(tempStudent);

			// commit transaction
			session.getTransaction().commit();

			return Result.SUCCESS;

		} catch (Exception e) {
			e.getStackTrace();
			return Result.FAILURE;
		} finally {
			session.close();
		}

	}

	// Delete Method
	public Result delete(Patron patron) {

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			Patron tempPatron = session.get(Patron.class, patron.getId());
			session.delete(tempPatron);
			session.getTransaction().commit();
			return Result.SUCCESS;

		} catch (NullPointerException e) {
			System.out.println("No such record found to be deleted!");
		} finally {
			session.close();
		}
		return Result.FAILURE;
	}

	public Result update(Patron patron) {

		Session session = factory.getCurrentSession();
		try {

			session.beginTransaction();
			Patron tempPatron = session.get(Patron.class, patron.getId());

			String name = patron.getName();

			byte[] image = patron.getImage();

			tempPatron.setName(name);
			tempPatron.setImage(image);
			session.getTransaction().commit();
			return Result.SUCCESS;

		} catch (NullPointerException e) {
			System.out.println("No such record found");
		} finally {
			session.close();
		}
		return Result.FAILURE;
	}

	public List<Patron> findPatron(String name) {
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			List<Patron> pList = session.createQuery("from Patron p where p.name =:name").setParameter("name", name)
					.getResultList();

			session.getTransaction().commit();

			return pList;

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return null;
	}

	public Patron findPatron(int id) {
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			Patron tempPatron = session.get(Patron.class, id);

			session.getTransaction().commit();

			return tempPatron;

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return null;
	}

	//// Bank
	public Result add(Bank bank) {

		Session session = factory.getCurrentSession();

		try {
			Bank tempBank = new Bank(bank.getId(), bank.getName());

			session.beginTransaction();

			// save the bank object
			session.save(tempBank);

			// commit transaction
			session.getTransaction().commit();

			return Result.SUCCESS;

		} catch (Exception e) {
			System.out.println(e);
			return Result.FAILURE;
		} finally {
			session.close();
		}
	}

	public Result remove(Bank bank) {

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			Bank tempBank = session.get(Bank.class, bank.getId());
			session.delete(tempBank);
			session.getTransaction().commit();
			return Result.SUCCESS;

		} catch (NullPointerException e) {
			System.out.println("No such record found to be deleted!");
		} finally {
			session.close();
		}
		return Result.FAILURE;
	}

	public Result update(Bank bank) {

		Session session = factory.getCurrentSession();
		try {

			session.beginTransaction();
			Bank tempBank = session.get(Bank.class, bank.getId());
			String name = bank.getName();

			tempBank.setName(name);

			session.getTransaction().commit();

			return Result.SUCCESS;

		} catch (NullPointerException e) {
			System.out.println("No such record found");
		} finally {
			session.close();
		}
		return Result.FAILURE;
	}

	public Bank findBank(int id) {

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			Bank tempBank = session.get(Bank.class, id);

			session.getTransaction().commit();

			return tempBank;

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return null;
	}

	public List<Bank> findBank(String name) {

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			List<Bank> bList = session.createQuery("from Bank b where b.name=:name").setParameter("name", name)
					.getResultList();
			session.getTransaction().commit();
			return bList;

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return null;
	}

	// Account
	public Result add(Account account) {

		Session session = factory.getCurrentSession();

		try {

			Patron tempPatron = account.getPatron();
			Bank tempBank = account.getBank();
			if (tempPatron == null || tempBank == null)
				return Result.FAILURE;

			session.beginTransaction();
			Account tempAccount = new Account(account.getId(), tempBank, tempPatron);

			session.save(tempAccount);

			session.getTransaction().commit();

			return Result.SUCCESS;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return Result.FAILURE;
	}

	public Result update(Account account) {

		Session session = factory.getCurrentSession();
		try {

			session.beginTransaction();
			Patron tempPatron = account.getPatron();
			Bank tempBank = account.getBank();
			if (tempPatron == null || tempBank == null)
				return Result.FAILURE;

			Account tempAccount = session.get(Account.class, account.getId());

			tempAccount.setPatron(tempPatron);
			tempAccount.setBank(tempBank);

			session.getTransaction().commit();

			return Result.SUCCESS;

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return Result.FAILURE;
	}

	public Result delete(Account account) {

		Session session = factory.getCurrentSession();
		try {

			session.beginTransaction();
			Account tempAccount = session.get(Account.class, account.getId());
			session.delete(tempAccount);
			session.getTransaction().commit();
			return Result.SUCCESS;

		} catch (NullPointerException e) {
			System.out.println("No such record found to be deleted!");
		} finally {
			session.close();
		}
		return Result.FAILURE;

	}

	public Account findAccount(int id) {

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			Account tempAccount = session.get(Account.class, id);

			session.getTransaction().commit();

			return tempAccount;

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return null;

	}

	public Result transact(Transaction transaction) {

		Session session = factory.getCurrentSession();

		try {

			Account tempAccount = transaction.getAccount();
			Transaction tempTransaction = new Transaction(transaction.getId(), tempAccount, transaction.getAmount(),
					transaction.getAccountType());

			session.beginTransaction();

			// save the bank object
			session.save(tempTransaction);

			// commit transaction
			session.getTransaction().commit();

			return Result.SUCCESS;

		} catch (Exception e) {
			System.out.println(e);
			return Result.FAILURE;
		} finally {
			session.close();
		}
	}

	public Transaction findTransaction(int id) {

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			Transaction tempTransaction = session.get(Transaction.class, id);

			session.getTransaction().commit();

			return tempTransaction;

		} catch (NullPointerException e) {
			System.out.println("No such transaction found!");
		} finally {
			session.close();
		}
		return null;

	}

}
