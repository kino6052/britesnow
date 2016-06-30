package com.example.addressbook.dao;

import java.util.List;
import java.util.Map;

import com.britesnow.snow.util.MapUtil;
import com.example.addressbook.entity.Group;
import com.google.inject.Singleton;

@Singleton
public class GroupDao extends BaseHibernateDao<Group> {
	// TODO: How to Find Items Which are Assigned to Multiple Groups?
	public Group getGroupById(Long id){
        return (Group) daoHelper.findFirst("from " + entityClass.getSimpleName() + " where group_id = ?", id);
    }
	
	public List<Group> getGroupsForContact(Long contactId){
        Map matchProp = MapUtil.mapIt("contact_id", contactId);
        return list(0,300,matchProp,"title",null);
    }
}


