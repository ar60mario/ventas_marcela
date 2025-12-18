/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.bo;

import ar.com.ventas.dao.TicketTimeDAO;
import ar.com.ventas.entities.TicketTime;
import ar.com.ventas.entities.TitularCuit;
import org.hibernate.HibernateException;

/**
 *
 * @author Mario
 */
public class TicketTimeBO {

    TicketTimeDAO dao = new TicketTimeDAO();

    public TicketTime updateTicket(TicketTime ticket) throws Exception {
        try {
            ticket = (TicketTime) dao.update(ticket);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return ticket;
    }
    
    public TicketTime saveTicket(TicketTime ticket) throws Exception {
        try {
            ticket = (TicketTime) dao.save(ticket);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return ticket;
    }

    public TicketTime getTicketById(Long id) throws Exception {
        TicketTime ticket = null;
        try{
            ticket = (TicketTime) dao.getById(TicketTime.class, id);
        }catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return ticket;
    }
    
    public TicketTime getTicketByTitularCuit(TitularCuit titular) throws Exception {
        TicketTime ticket = null;
        try{
            ticket = (TicketTime) dao.getTicketByTitularCuit(titular);
        }catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return ticket;
    }
}
