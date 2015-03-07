package com.yuansewenhua.dao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;

import com.yuansewenhua.dao.Foods;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table FOODS.
*/
public class FoodsDao extends AbstractDao<Foods, Long> {

    public static final String TABLENAME = "FOODS";

    /**
     * Properties of entity Foods.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Price = new Property(2, String.class, "price", false, "PRICE");
        public final static Property Smallimagepath = new Property(3, String.class, "smallimagepath", false, "SMALLIMAGEPATH");
        public final static Property Bigimagepath = new Property(4, String.class, "bigimagepath", false, "BIGIMAGEPATH");
        public final static Property Yongliao = new Property(5, String.class, "yongliao", false, "YONGLIAO");
        public final static Property Flavour = new Property(6, String.class, "flavour", false, "FLAVOUR");
        public final static Property Memo = new Property(7, String.class, "memo", false, "MEMO");
        public final static Property Issu = new Property(8, Boolean.class, "issu", false, "ISSU");
        public final static Property Isliang = new Property(9, Boolean.class, "isliang", false, "ISLIANG");
        public final static Property Isqingzhen = new Property(10, Boolean.class, "isqingzhen", false, "ISQINGZHEN");
        public final static Property Clickcount = new Property(11, String.class, "clickcount", false, "CLICKCOUNT");
        public final static Property Isenable = new Property(12, boolean.class, "isenable", false, "ISENABLE");
        public final static Property Typetitle = new Property(13, String.class, "typetitle", false, "TYPETITLE");
        public final static Property Foodstypeid = new Property(14, Long.class, "foodstypeid", false, "FOODSTYPEID");
    };

    private DaoSession daoSession;


    public FoodsDao(DaoConfig config) {
        super(config);
    }
    
    public FoodsDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'FOODS' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'NAME' TEXT NOT NULL ," + // 1: name
                "'PRICE' TEXT NOT NULL ," + // 2: price
                "'SMALLIMAGEPATH' TEXT NOT NULL ," + // 3: smallimagepath
                "'BIGIMAGEPATH' TEXT NOT NULL ," + // 4: bigimagepath
                "'YONGLIAO' TEXT NOT NULL ," + // 5: yongliao
                "'FLAVOUR' TEXT NOT NULL ," + // 6: flavour
                "'MEMO' TEXT," + // 7: memo
                "'ISSU' INTEGER," + // 8: issu
                "'ISLIANG' INTEGER," + // 9: isliang
                "'ISQINGZHEN' INTEGER," + // 10: isqingzhen
                "'CLICKCOUNT' TEXT," + // 11: clickcount
                "'ISENABLE' INTEGER NOT NULL ," + // 12: isenable
                "'TYPETITLE' TEXT," + // 13: typetitle
                "'FOODSTYPEID' INTEGER);"); // 14: foodstypeid
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'FOODS'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Foods entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getName());
        stmt.bindString(3, entity.getPrice());
        stmt.bindString(4, entity.getSmallimagepath());
        stmt.bindString(5, entity.getBigimagepath());
        stmt.bindString(6, entity.getYongliao());
        stmt.bindString(7, entity.getFlavour());
 
        String memo = entity.getMemo();
        if (memo != null) {
            stmt.bindString(8, memo);
        }
 
        Boolean issu = entity.getIssu();
        if (issu != null) {
            stmt.bindLong(9, issu ? 1l: 0l);
        }
 
        Boolean isliang = entity.getIsliang();
        if (isliang != null) {
            stmt.bindLong(10, isliang ? 1l: 0l);
        }
 
        Boolean isqingzhen = entity.getIsqingzhen();
        if (isqingzhen != null) {
            stmt.bindLong(11, isqingzhen ? 1l: 0l);
        }
 
        String clickcount = entity.getClickcount();
        if (clickcount != null) {
            stmt.bindString(12, clickcount);
        }
        stmt.bindLong(13, entity.getIsenable() ? 1l: 0l);
 
        String typetitle = entity.getTypetitle();
        if (typetitle != null) {
            stmt.bindString(14, typetitle);
        }
 
        Long foodstypeid = entity.getFoodstypeid();
        if (foodstypeid != null) {
            stmt.bindLong(15, foodstypeid);
        }
    }

    @Override
    protected void attachEntity(Foods entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Foods readEntity(Cursor cursor, int offset) {
        Foods entity = new Foods( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // name
            cursor.getString(offset + 2), // price
            cursor.getString(offset + 3), // smallimagepath
            cursor.getString(offset + 4), // bigimagepath
            cursor.getString(offset + 5), // yongliao
            cursor.getString(offset + 6), // flavour
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // memo
            cursor.isNull(offset + 8) ? null : cursor.getShort(offset + 8) != 0, // issu
            cursor.isNull(offset + 9) ? null : cursor.getShort(offset + 9) != 0, // isliang
            cursor.isNull(offset + 10) ? null : cursor.getShort(offset + 10) != 0, // isqingzhen
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // clickcount
            cursor.getShort(offset + 12) != 0, // isenable
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // typetitle
            cursor.isNull(offset + 14) ? null : cursor.getLong(offset + 14) // foodstypeid
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Foods entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.getString(offset + 1));
        entity.setPrice(cursor.getString(offset + 2));
        entity.setSmallimagepath(cursor.getString(offset + 3));
        entity.setBigimagepath(cursor.getString(offset + 4));
        entity.setYongliao(cursor.getString(offset + 5));
        entity.setFlavour(cursor.getString(offset + 6));
        entity.setMemo(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setIssu(cursor.isNull(offset + 8) ? null : cursor.getShort(offset + 8) != 0);
        entity.setIsliang(cursor.isNull(offset + 9) ? null : cursor.getShort(offset + 9) != 0);
        entity.setIsqingzhen(cursor.isNull(offset + 10) ? null : cursor.getShort(offset + 10) != 0);
        entity.setClickcount(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setIsenable(cursor.getShort(offset + 12) != 0);
        entity.setTypetitle(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setFoodstypeid(cursor.isNull(offset + 14) ? null : cursor.getLong(offset + 14));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Foods entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Foods entity) {
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
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getFoodstypeDao().getAllColumns());
            builder.append(" FROM FOODS T");
            builder.append(" LEFT JOIN FOODSTYPE T0 ON T.'FOODSTYPEID'=T0.'_id'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Foods loadCurrentDeep(Cursor cursor, boolean lock) {
        Foods entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Foodstype foodstype = loadCurrentOther(daoSession.getFoodstypeDao(), cursor, offset);
        entity.setFoodstype(foodstype);

        return entity;    
    }

    public Foods loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Foods> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Foods> list = new ArrayList<Foods>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Foods> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Foods> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}