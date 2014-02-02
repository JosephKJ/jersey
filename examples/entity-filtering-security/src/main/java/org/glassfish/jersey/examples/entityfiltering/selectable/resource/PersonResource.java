/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package org.glassfish.jersey.examples.entityfiltering.selectable.resource;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.glassfish.jersey.examples.entityfiltering.security.domain.RestrictedEntity;
import org.glassfish.jersey.examples.entityfiltering.selectable.domain.Address;
import org.glassfish.jersey.examples.entityfiltering.selectable.domain.Person;
import org.glassfish.jersey.examples.entityfiltering.selectable.domain.PhoneNumber;

/**
 * Resource restricted with security annotations. Security restrictions are defined by resource methods and
 * {@link RestrictedEntity}.
 * 
 * @author Andy Pemberton (pembertona at gmail.com)
 */
@Path("people")
@Produces("application/json")
public class PersonResource {

    @GET
    @Path("{id}")
    public Person getPerson() {
        Person person = new Person();
        person.setGivenName("Andrew");
        person.setFamilyName("Dowd");
        person.setHonorificPrefix("Mr.");
        person.setRegion("1st Level Region");
        person.setAddresses(new ArrayList<Address>());
        person.getAddresses().add(new Address());
        person.getAddresses().get(0).setRegion("2nd Level Region");
        person.getAddresses().get(0).setStreetAddress("1234 fake st.");
        person.getAddresses().get(0).setPhoneNumber(new PhoneNumber());
        person.getAddresses().get(0).getPhoneNumber().setNumber("867-5309");
        person.getAddresses().get(0).getPhoneNumber().setAreaCode("540");

        person.setPhoneNumbers(new HashMap<String, PhoneNumber>());
        PhoneNumber number = new PhoneNumber();
        number.setAreaCode("804");
        number.setNumber("867-5309");
        person.getPhoneNumbers().put("HOME", number);

        return person;
    }

    @GET
    @Path("{id}/addresses")
    public Address getAddress() {
        return this.getPerson().getAddresses().get(0);
    }
}
