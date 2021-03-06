/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.openjpa.jdbc.meta.strats;

import java.sql.SQLException;

import org.apache.openjpa.jdbc.kernel.JDBCFetchConfiguration;
import org.apache.openjpa.jdbc.kernel.JDBCStore;
import org.apache.openjpa.jdbc.meta.FieldMapping;
import org.apache.openjpa.jdbc.meta.FieldStrategy;
import org.apache.openjpa.jdbc.sql.Joins;
import org.apache.openjpa.jdbc.sql.Result;
import org.apache.openjpa.jdbc.sql.RowManager;
import org.apache.openjpa.jdbc.sql.SQLBuffer;
import org.apache.openjpa.jdbc.sql.Select;
import org.apache.openjpa.jdbc.sql.SelectExecutor;
import org.apache.openjpa.kernel.OpenJPAStateManager;
import org.apache.openjpa.lib.util.Localizer;
import org.apache.openjpa.util.MetaDataException;

/**
 * No-op strategy for easy extension.
 *
 * @author Abe White
 */
public abstract class AbstractFieldStrategy
    extends AbstractStrategy
    implements FieldStrategy {

    
    private static final long serialVersionUID = 1L;

    private static final Localizer _loc = Localizer.forPackage
        (AbstractFieldStrategy.class);

    /**
     * The owning field mapping.
     */
    protected FieldMapping field = null;

    /**
     * Throws an informative exception if the field declares a mapped-by value.
     */
    protected void assertNotMappedBy() {
        if (field != null && field.getMappedBy() != null)
            throw new MetaDataException(_loc.get("cant-mapped-by", field,
                getAlias()));
    }

    @Override
    public void setFieldMapping(FieldMapping owner) {
        field = owner;
    }

    @Override
    public int supportsSelect(Select sel, int type, OpenJPAStateManager sm,
        JDBCStore store, JDBCFetchConfiguration fetch) {
        return 0;
    }

    @Override
    public void selectEagerJoin(Select sel, OpenJPAStateManager sm,
        JDBCStore store, JDBCFetchConfiguration fetch, int eagerMode) {
    }

    @Override
    public void selectEagerParallel(SelectExecutor sel, OpenJPAStateManager sm,
        JDBCStore store, JDBCFetchConfiguration fetch, int eagerMode) {
    }

    @Override
    public boolean isEagerSelectToMany() {
        return false;
    }

    @Override
    public int select(Select sel, OpenJPAStateManager sm, JDBCStore store,
        JDBCFetchConfiguration fetch, int eagerMode) {
        return -1;
    }

    @Override
    public Object loadEagerParallel(OpenJPAStateManager sm, JDBCStore store,
        JDBCFetchConfiguration fetch, Object res)
        throws SQLException {
        return res;
    }

    @Override
    public void loadEagerJoin(OpenJPAStateManager sm, JDBCStore store,
        JDBCFetchConfiguration fetch, Result res)
        throws SQLException {
    }

    @Override
    public void load(OpenJPAStateManager sm, JDBCStore store,
        JDBCFetchConfiguration fetch, Result res)
        throws SQLException {
    }

    @Override
    public void load(OpenJPAStateManager sm, JDBCStore store,
        JDBCFetchConfiguration fetch)
        throws SQLException {
    }

    @Override
    public Object toDataStoreValue(Object val, JDBCStore store) {
        return val;
    }

    @Override
    public Object toKeyDataStoreValue(Object val, JDBCStore store) {
        return val;
    }

    @Override
    public void appendIsEmpty(SQLBuffer sql, Select sel, Joins joins) {
        sql.append("1 <> 1");
    }

    @Override
    public void appendIsNotEmpty(SQLBuffer sql, Select sel, Joins joins) {
        sql.append("1 = 1");
    }

    @Override
    public void appendIsNull(SQLBuffer sql, Select sel, Joins joins) {
        sql.append("1 <> 1");
    }

    @Override
    public void appendIsNotNull(SQLBuffer sql, Select sel, Joins joins) {
        sql.append("1 <> 1");
    }

    @Override
    public void appendSize(SQLBuffer sql, Select sel, Joins joins) {
        sql.append("1");
    }

    @Override
    public void appendIndex(SQLBuffer sql, Select sel, Joins joins) {
        sql.append("1");
    }

    @Override
    public void appendType(SQLBuffer sql, Select sel, Joins joins) {
        sql.append("1");
    }

    @Override
    public Joins join(Joins joins, boolean forceOuter) {
        return joins;
    }

    @Override
    public Joins joinKey(Joins joins, boolean forceOuter) {
        return joins;
    }

    @Override
    public Joins joinRelation(Joins joins, boolean forceOuter,
        boolean traverse) {
        return joins;
    }

    @Override
    public Joins joinKeyRelation(Joins joins, boolean forceOuter,
        boolean traverse) {
        return joins;
    }

    @Override
    public Object loadProjection(JDBCStore store, JDBCFetchConfiguration fetch,
        Result res, Joins joins)
        throws SQLException {
        return null;
    }

    @Override
    public Object loadKeyProjection(JDBCStore store,
        JDBCFetchConfiguration fetch, Result res, Joins joins)
        throws SQLException {
        return null;
    }

    @Override
    public boolean isVersionable() {
        return false;
    }

    @Override
    public void where(OpenJPAStateManager sm, JDBCStore store, RowManager rm,
        Object prevValue)
        throws SQLException {
    }
}
