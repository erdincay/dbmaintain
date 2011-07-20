/*
 * Copyright DbMaintain.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dbmaintain.launch.ant;

/**
 * @author Filip Neven
 * @author Tim Ducheyne
 */
public class Database {

    private String name;
    private boolean included = true;
    private String dialect;
    private String driverClassName;
    private String url;
    private String userName;
    private String password;
    private String schemaNames;

    public Database() {
        // needed for ant
    }

    public Database(String name, boolean included, String dialect, String driverClassName, String url, String userName, String password, String schemaNames) {
        this.name = name;
        this.included = included;
        this.dialect = dialect;
        this.driverClassName = driverClassName;
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.schemaNames = schemaNames;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIncluded() {
        return included;
    }

    public void setIncluded(boolean included) {
        this.included = included;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setSchemaNames(String schemaNames) {
        this.schemaNames = schemaNames;
    }

    public String getSchemaNames() {
        return schemaNames;
    }
}
