package com.yuansewenhua.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.yuansewenhua.dao.Porelationships;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table PORELATIONSHIPS.
*/
public class PorelationshipsDao extends AbstractDao<Porelationships, Long> {

    public static final String TABLENAME = "PORELATIONSHIPS";

    /**
     * Properties of entity Porelationships.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Orderid = new Property(1, Long.class, "orderid", false, "ORDERID");
        public final static Property Productid = new Property(2, Long.class, "productid", false, "PRODUCTID");
        public final static Property Status = new Property(3, String.class, "status", false, "STATUS");
        public final static Property Producttyppe = new Property(4, String.class, "producttyppe", false, "PRODUCTTYPPE");
        public final static Property Productcount = new Property(5, Integer.class, "productcount", false, "PRODUCTCOUNT");
    };


    public PorelationshipsDao(DaoConfig config) {
        super(config);
    }
    
    public PorelationshipsDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'PORELATIONSHIPS' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'ORDERID' INTEGER," + // 1: orderid
                "'PRODUCTID' INTEGER," + // 2: productid
                "'STATUS' TEXT," + // 3: status
                "'PRODUCTTYPPE' TEXT," + // 4: producttyppe
                "'PRODUCTCOUNT' INTEGER);"); // 5: productcount
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'PORELATIONSHIPS'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Porelationships entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long orderid = entity.getOrderid();
        if (orderid != null) {
            stmt.bindLong(2, orderid);
        }
 
        Long productid = entity.getProductid();
        if (productid != null) {
            stmt.bindLong(3, productid);
        }
 
        String status = entity.getStatus();
        if (status != null) {
            stmt.bindString(4, status);
        }
 
        String producttyppe = entity.getProducttyppe();
        if (producttyppe != null) {
            stmt.bindString(5, producttyppe);
        }
 
        Integer productcount = entity.getProductcount();
        if (productcount != null) {
            stmt.bindLong(6, productcount);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Porelationships readEntity(Cursor cursor, int offset) {
        Porelationships entity = new Porelationships( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // orderid
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // productid
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // status
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // producttyppe
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5) // productcount
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Porelationships entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setOrderid(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setProductid(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setStatus(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setProducttyppe(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setProductcount(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Porelationships entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Porelationships entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
