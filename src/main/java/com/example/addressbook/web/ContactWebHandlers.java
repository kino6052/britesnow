package com.example.addressbook.web;

import java.util.List;

import javax.inject.Inject;

import com.britesnow.snow.web.param.annotation.PathVar;
import com.britesnow.snow.web.param.annotation.WebParam;
import com.britesnow.snow.web.param.annotation.WebUser;
import com.britesnow.snow.web.rest.annotation.WebDelete;
import com.britesnow.snow.web.rest.annotation.WebGet;
import com.britesnow.snow.web.rest.annotation.WebPost;
import com.example.addressbook.dao.ContactDao;
import com.example.addressbook.dao.ItemDao;
import com.example.addressbook.entity.Contact;
import com.example.addressbook.entity.Group;
import com.example.addressbook.entity.User;
import com.google.inject.Singleton;

@Singleton
public class ContactWebHandlers {

    @Inject
    public ContactDao contactDao;

    @WebPost("/api/contacts")
    public WebResponse apiUserCreateItem(
    						@WebParam("first_name") String first_name,
    						@WebParam("last_name") String last_name,
    						@WebParam("phone") String phone
                            ) {
//        if (user != null) {
            try {
                Contact contact = new Contact(first_name, last_name, phone);
                contact = contactDao.save(contact);
                return WebResponse.success(contact);
            } catch (Throwable t) {
                return WebResponse.fail(t);
            }
//        }
//        return WebResponse.fail("Not logged in, no create.");
    }

    @WebDelete("/api/contacts/{contactId}")
    public WebResponse apiUserDeleteItem(@WebUser User user, @PathVar("contactId") Long contactId) {
//        if (user != null) {
            try {
                contactDao.delete(contactId);
                return WebResponse.success(contactId);
            } catch (Throwable t) {
                return WebResponse.fail(t);
            }
//        }
//        return WebResponse.fail("Not logged in, no delete.");
    }

    @WebGet("/api/contacts/group/{groupId}")
    public WebResponse apiGroupContacts(@WebUser User user, @PathVar("groupId") Long groupId) {
//        if (user != null) {
            try {
                List<Contact> contacts = contactDao.getContactsForGroup(groupId);
                return WebResponse.success(contacts);
            } catch (Throwable t) {
                return WebResponse.fail(t);
            }
//        }
//        return WebResponse.fail("Not logged in, no item list.");
    }
    
    @WebGet("/api/contacts/{contactId}")
    public WebResponse apiContacts(@WebUser User user, @PathVar("contactId") Long contactId) {
//        if (user != null) {
            try {
                Contact contact = contactDao.getContactById(contactId);
                return WebResponse.success(contact);
            } catch (Throwable t) {
                return WebResponse.fail(t);
            }
//        }
//        return WebResponse.fail("Not logged in, no item list.");
    }
    
    @WebGet("/api/contacts")
    public WebResponse apiContacts() {
//        if (user != null) {
            try {
                List<Contact> contactList = contactDao.getContacts();
                return WebResponse.success(contactList);
            } catch (Throwable t) {
                return WebResponse.fail(t);
            }
//        }
//        return WebResponse.fail("Not logged in, no item list.");
    }

}