package in.co.rays.proj3.model;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.proj3.dto.CollegeDTO;
import in.co.rays.proj3.dto.CourseDTO;
import in.co.rays.proj3.dto.FecultyDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.exception.RecordNotFoundException;
import in.co.rays.proj3.util.HIBdatasource;

/**
 * Hibernate Implementation of FacultyModel.
 * @author Iterator
 * @version 1.0
 */
public class FecultyModelHBI implements FecultyModelINT {

	
	   /**
   	 * add a Faculty.
   	 *
   	 * @param dto the dto
   	 * @return the long
   	 * @throws ApplicationException the application exception
   	 * @throws DuplicateRecordException the duplicate record exception
   	 */
	public long add(FecultyDTO dto) throws DuplicateRecordException, ApplicationException {
		   Session s= HIBdatasource.getsession();
		   Transaction tx=null;
		   FecultyDTO dtoexist=findByEmail(dto.getEmail());
		   if(dtoexist!=null){
			   throw new DuplicateRecordException("Record already exist");
		   }
		   CourseModelINT cmodel=  ModelFactory.getInstance().getCourseModel();
		   CollegeModelINT comodel= ModelFactory.getInstance().getCollegeModel();
		   CourseDTO cdto= cmodel.findByPK(dto.getCourseId());
		   CollegeDTO codto=comodel.findByPk(dto.getCollegeId());
		   dto.setCollegeName(codto.getName());
		   dto.setCourseName(cdto.getCoursename());
		   
		   try{
			   tx= s.beginTransaction();
			   s.save(dto);
			   tx.commit();
		   }
		   catch(HibernateException e){
			   e.printStackTrace();
			   if(tx!=null){
				   tx.rollback();
			   }
		   }
		   finally{
			   HIBdatasource.closesession(s);
		   }

		return 0;
	}

	
	/**
	 * Delete a Faculty.
	 *
	 * @param dto the dto
	 * @throws ApplicationException the application exception
	 */
	public void delete(FecultyDTO dto) throws ApplicationException {
		   Session s= HIBdatasource.getsession();
		   Transaction tx=null;
		   try{
			   tx= s.beginTransaction();
			   s.delete(dto);
			   tx.commit();
		   }
		   catch(HibernateException e){
			   tx.rollback();
			   throw new ApplicationException("Getting error to delete record");
		   }
		   finally{
			   HIBdatasource.closesession(s);
		   }

	}

	
	/**
	 * Update a Faculty.
	 *
	 * @param dto the dto
	 * @throws ApplicationException the application exception
	 * @throws DuplicateRecordException the duplicate record exception
	 */
	public void update(FecultyDTO dto) throws ApplicationException, DuplicateRecordException {
		   Session s= HIBdatasource.getsession();
		   Transaction tx=null;
		   CourseModelINT cmodel=  ModelFactory.getInstance().getCourseModel();
		   CollegeModelINT comodel= ModelFactory.getInstance().getCollegeModel();
		   CourseDTO cdto= cmodel.findByPK(dto.getCourseId());
		   CollegeDTO codto=comodel.findByPk(dto.getCollegeId());
		   dto.setCollegeName(codto.getName());
		   dto.setCourseName(cdto.getCoursename());
		  
		   try{
			   tx=s.beginTransaction();
			   s.update(dto);
			   tx.commit();
		   }
		   catch(HibernateException e){
			   tx.rollback();
			   e.printStackTrace();
			   throw new ApplicationException("Getting to update record");
		   }
		   finally{
			   HIBdatasource.closesession(s);
		   }
		

	}

	
	/* (non-Javadoc)
	 * @see in.co.rays.model.FacultyModelInt#findByPK(long)
	 */
	public FecultyDTO findByPK(long pk) throws RecordNotFoundException {
		   Session s= HIBdatasource.getsession();
		   FecultyDTO dto =null;
		   try{
			   dto=(FecultyDTO) s.get(FecultyDTO.class, pk);
		   }
		   catch(HibernateException e){
			   throw new RecordNotFoundException("No Record found");
		   }
		   finally{
			   HIBdatasource.closesession(s);
		   }
	
		
		return dto;
	}

	public FecultyDTO findByEmail(String Email) throws ApplicationException {
		   Session s= HIBdatasource.getsession();
		   FecultyDTO dto =null;
		   try{
			   Criteria cr=s.createCriteria(FecultyDTO.class);
			   cr.add(Restrictions.eq("email", Email));
			   List list= cr.list();
			   Iterator it = list.iterator();
			   while(it.hasNext()){
				   dto=(FecultyDTO) it.next();
			   }
		   }
		   catch(HibernateException e){
			   throw new ApplicationException("No Record found");
		   }
		   finally{
			   HIBdatasource.closesession(s);
		   }
		return dto;
	}

	
	/* (non-Javadoc)
	 * @see in.co.rays.model.FacultyModelInt#search(in.co.rays.dto.FacultyDTO, int, int)
	 */
	public List search(FecultyDTO dto, int pageNo, int pageSize) throws ApplicationException {
		   System.out.println("Feculty model search method called");
		   Session s= HIBdatasource.getsession();
		   Criteria cr=s.createCriteria(FecultyDTO.class);
		   List list=null;
		   try{
			   if(dto!=null){
				   System.out.println("DTO not null");
				   if(dto.getCollegeId()>0){
					   cr.add(Restrictions.eq("collegeId", dto.getCollegeId()));
				   }
				   if(dto.getCourseId()>0 ){
					   System.out.println("course id null nahi hai");
					   cr.add(Restrictions.eq("courseId", dto.getCourseId()));
				   }
				   if(dto.getSubjectId()>0){
					   cr.add(Restrictions.eq("subjectId", dto.getSubjectId()));
				   }
				   if(dto.getFirstName()!=null){
					   cr.add(Restrictions.like("firstname", dto.getFirstName()+"%"));
				   }
				   if(dto.getEmail()!=null){
					   cr.add(Restrictions.like("email", dto.getEmail()+"%"));
				   }
			   }
			   if(pageSize>0){
				   cr.setFirstResult(((pageNo-1)*pageSize));
				   cr.setMaxResults(pageSize);
			   }
			   list=cr.list();   
		   }
		   catch(HibernateException e){
			   e.printStackTrace();
			   
		   }
		   finally{
			   HIBdatasource.closesession(s);
		   }
		return list;
	}

	
	/* (non-Javadoc)
	 * @see in.co.rays.model.FacultyModelInt#search(in.co.rays.dto.FacultyDTO)
	 */
	public List search(FecultyDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto,0,0);
	}
	
	
	/* (non-Javadoc)
	 * @see in.co.rays.model.FacultyModelInt#list(int, int)
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		   Session s= HIBdatasource.getsession();
		   Criteria cr=s.createCriteria(FecultyDTO.class);
		   if(pageSize>0){
			   cr.setFirstResult(((pageNo-1)*pageSize));
			   cr.setMaxResults(pageSize);
		   }
		   List list =cr.list();
		return list;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.FacultyModelInt#list()
	 */
	public List list() throws ApplicationException {
		
		return list(0,0);
	}

}
