package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.dto.Coordonnee;
import com.dto.Ville;

@Repository
public class VilleDAOImpl implements VilleDAO{

	private FactoryDAO factoryDAO = FactoryDAO.getInstance();

	public ArrayList<Ville> findAllVilles(){

		ArrayList<Ville> listVille = new ArrayList<Ville>();

		try (
				Connection connection = factoryDAO.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT * FROM ville_france;");
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				Ville ville = new Ville();
				ville.setCodeCommune(resultSet.getString("Code_commune_INSEE"));
				ville.setNomCommune(resultSet.getString("Nom_commune"));
				ville.setCodePostal(resultSet.getString("Code_postal"));
				ville.setLibelleAcheminement(resultSet.getString("Libelle_acheminement"));
				ville.setLigne(resultSet.getString("Ligne_5"));
				ville.setCoordonnee(new Coordonnee(resultSet.getDouble("Latitude"), resultSet.getDouble("Longitude")));
				listVille.add(ville);
			}

			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("findAllVilles");
		return listVille;
	}
	
	public ArrayList<Ville> findVille(String code){

		ArrayList<Ville> listVille = new ArrayList<Ville>();

		try (
				Connection connection = factoryDAO.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT * FROM ville_france WHERE Code_commune_INSEE = '"+ code +"';");
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				Ville ville = new Ville();
				ville.setCodeCommune(resultSet.getString("Code_commune_INSEE"));
				ville.setNomCommune(resultSet.getString("Nom_commune"));
				ville.setCodePostal(resultSet.getString("Code_postal"));
				ville.setLibelleAcheminement(resultSet.getString("Libelle_acheminement"));
				ville.setLigne(resultSet.getString("Ligne_5"));
				ville.setCoordonnee(new Coordonnee(resultSet.getDouble("Latitude"), resultSet.getDouble("Longitude")));
				listVille.add(ville);
			}

			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("findAllVilles");
		return listVille;
	}
	
	public boolean updateVille(Ville ville) {
	    boolean success = false;
	    try (
	            Connection connection = factoryDAO.getConnection();
	            PreparedStatement statement = connection.prepareStatement(
	                    "UPDATE ville_france SET Nom_commune=?, Code_postal=?, Libelle_acheminement=?, Ligne_5=?, Latitude=?, Longitude=? WHERE Code_commune_INSEE=?")
	    ) {
	        statement.setString(1, ville.getNomCommune());
	        statement.setString(2, ville.getCodePostal());
	        statement.setString(3, ville.getLibelleAcheminement());
	        statement.setString(4, ville.getLigne());
	        statement.setDouble(5, ville.getCoordonnee().getLatitude());
	        statement.setDouble(6, ville.getCoordonnee().getLongitude());
	        statement.setString(7, ville.getCodeCommune());

	        int rowsAffected = statement.executeUpdate();

	        if (rowsAffected > 0) {
	            success = true;
	        }

	        statement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return success;
	}

	public boolean createVille(Ville ville) {
	    boolean success = false;
	    try (
	            Connection connection = factoryDAO.getConnection();
	            PreparedStatement statement = connection.prepareStatement(
	                    "INSERT INTO ville_france (Code_commune_INSEE, Nom_commune, Code_postal, Libelle_acheminement, Ligne_5, Latitude, Longitude) VALUES (?, ?, ?, ?, ?, ?, ?)")
	    ) {
	        statement.setString(1, ville.getCodeCommune());
	        statement.setString(2, ville.getNomCommune());
	        statement.setString(3, ville.getCodePostal());
	        statement.setString(4, ville.getLibelleAcheminement());
	        statement.setString(5, ville.getLigne());
	        statement.setDouble(6, ville.getCoordonnee().getLatitude());
	        statement.setDouble(7, ville.getCoordonnee().getLongitude());

	        int rowsAffected = statement.executeUpdate();

	        if (rowsAffected > 0) {
	            success = true;
	        }

	        statement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return success;
	}

	public boolean deleteVille(String codeCommune) {
	    boolean success = false;
	    try (
	            Connection connection = factoryDAO.getConnection();
	            PreparedStatement statement = connection.prepareStatement(
	                    "DELETE FROM ville_france WHERE Code_commune_INSEE=?")
	    ) {
	        statement.setString(1, codeCommune);

	        int rowsAffected = statement.executeUpdate();

	        if (rowsAffected > 0) {
	            success = true;
	        }

	        statement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return success;
	}


}