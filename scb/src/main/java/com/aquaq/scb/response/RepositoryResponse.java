package com.aquaq.scb.response;

import lombok.Getter;
import lombok.Setter;

/**
 Contains methods to return a meaningful response when a SQL query is executed.
 Namely, update and delete queries, and also executing procedures and functions
 */

@Getter
@Setter
public class RepositoryResponse extends ScbResponse {


    /**
     *  This method sets the Repository response and Repository responseCode depending on the SQLResponseCode from the executed query
     *
     * @param SQLResponseCode - The response code from the repository when an insert/add query is executed
     */

    public void setResponseWhenRecordAdded(final int SQLResponseCode) {

        if (SQLResponseCode == 0) {
            this.setResponseCode(ResponseCodes.BAD_REQUEST_ERROR);
            this.setResponse("Failed to add record");
        } else {
            this.setResponse("Successfully added record");
        }
    }


    /**
     *  This method sets the Repository response and Repository responseCode depending on the SQLResponseCode from the executed query
     *
     * @param SQLResponseCode -  The response code from the repository when an update query is executed
     * @param idToUpdate - The unique id of the record to attempt update
     */
    public void setResponseWhenRecordUpdated(final int SQLResponseCode, final int idToUpdate) {

        if (SQLResponseCode == 0) {
            this.setResponseCode(ResponseCodes.BAD_REQUEST_ERROR);
            this.setResponse(String.format("Failed to update record with id = %s", idToUpdate));
        } else {
            this.setResponse(String.format("The record with id = %s has been updated", idToUpdate));
        }
    }

    /**
     *  This method sets the Repository response and Repository responseCode depending on the SQLResponseCode from the executed query.
     * This is an overloaded method for when the idToUpdate is a String
     *
     * @param SQLResponseCode
     * @param idToUpdate
     */
    public void setResponseWhenRecordUpdated(final int SQLResponseCode, final String idToUpdate) {

        if (SQLResponseCode == 0) {
            this.setResponseCode(ResponseCodes.BAD_REQUEST_ERROR);
            this.setResponse(String.format("Failed to update record with id = %s", idToUpdate));
        } else {
            this.setResponse(String.format("The record with id = %s has been updated", idToUpdate));
        }
    }

    /**
     * This method sets the Repository response and Repository responseCode depending on the SQLResponseCode from the executed query
     *
     * @param SQLResponseCode - response code whenever a record is deleted
     * @param idToDelete - The unique id of the record to attempt update
     */
    public void setResponseWhenRecordDeleted(final int SQLResponseCode, final int idToDelete) {
        if (SQLResponseCode == 0) {
            this.setResponseCode(ResponseCodes.BAD_REQUEST_ERROR);
            this.setResponse(String.format("There is nothing to delete because the record with id = %s does not exist", idToDelete));
        } else {
            this.setResponse(String.format("The record with id = %s has been deleted", idToDelete));
        }
    }


    /**
     * This method sets the response depending on the SQLResponseCode.
     * SQL Stored procedures return a SQLResponseCode 0 if the procedure is executed without errors
     *
     * @param SQLResponseCode
     */
    public void setResponseWhenSQLProcedureExecuted(final int SQLResponseCode) {

        if (SQLResponseCode != 0) {
            this.setResponseCode(ResponseCodes.BAD_REQUEST_ERROR);
            this.setResponse("Failed to execute SQL procedure");
        } else {
            this.setResponse(String.format("Successfully executed SQL procedure"));
        }
    }

    // SQL functions return a value of 0 if successful

    /**
     * This method sets the Repository response and Repository responseCode depending on the SQLResponseCode.
     * SQL Stored functions return a SQLResponseCode 0 if the function is executed without errors
     *
     * @param SQLResponseCode
     */
    public void setResponseWhenSQLFunctionExecuted(final int SQLResponseCode) {

        if (SQLResponseCode != 0) {
            this.setResponseCode(ResponseCodes.BAD_REQUEST_ERROR);
            this.setResponse("Failed to execute SQL function");
        } else {
            this.setResponse(String.format("Successfully executed SQL function"));
        }
    }
}
