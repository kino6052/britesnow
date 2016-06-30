package com.example.addressbook.dao;

import java.util.List;
import java.util.Map;

import com.britesnow.snow.util.MapUtil;
import com.example.addressbook.entity.Contact;
import com.google.inject.Singleton;

@Singleton
public class ContactDao extends BaseHibernateDao<Contact> {

	public Contact getContactById(Long id){
        return (Contact) daoHelper.findFirst("from " + entityClass.getSimpleName() + " where contact_id = ?", id);
    }
	
	public List<Contact> getContacts(){
		return list(0,300,null,null,null);
    }
	
    public List<Contact> getContactsForGroup(Long group_id){
        Map matchProp = MapUtil.mapIt("group_id", group_id);
        return list(0,300,matchProp,null,null);
    }
}
	

