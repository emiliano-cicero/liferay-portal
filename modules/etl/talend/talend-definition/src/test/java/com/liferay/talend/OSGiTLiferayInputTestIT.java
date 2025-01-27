/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.talend;

import static org.ops4j.pax.exam.CoreOptions.composite;
import static org.ops4j.pax.exam.CoreOptions.linkBundle;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.options;

import com.liferay.talend.tliferaybatchfile.TLiferayBatchFileDefinition;
import com.liferay.talend.tliferayconnection.TLiferayConnectionDefinition;
import com.liferay.talend.tliferayinput.TLiferayInputDefinition;
import com.liferay.talend.tliferayoutput.TLiferayOutputDefinition;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.OptionUtils;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.options.MavenArtifactProvisionOption;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;

import org.talend.components.api.ComponentsPaxExamOptions;

/**
 * @author Zoltán Takács
 */
@ExamReactorStrategy(PerClass.class)
@RunWith(PaxExam.class)
public class OSGiTLiferayInputTestIT extends LiferayAbstractComponentTestCase {

	@Configuration
	public Option[] config() {
		Option[] updatedOptions = OptionUtils.remove(
			MavenArtifactProvisionOption.class,
			ComponentsPaxExamOptions.getOptions());

		Option bundleOption1 = linkBundle(
			"org.talend.components-components-common-bundle");
		Option bundleOption2 = linkBundle(
			"com.liferay-com.liferay.talend.definition");
		Option apacheFelixOption = composite(
			options(
				mavenBundle(
				).groupId(
					"org.apache.felix"
				).artifactId(
					"org.apache.felix.scr"
				).version(
					"1.6.0"
				)));

		return options(
			composite(updatedOptions), apacheFelixOption, bundleOption1,
			bundleOption2);
	}

	@Test
	public void testComponentHasBeenRegistered() {
		assertComponentIsRegistered(
			TLiferayBatchFileDefinition.class,
			TLiferayBatchFileDefinition.COMPONENT_NAME);
		assertComponentIsRegistered(
			TLiferayConnectionDefinition.class,
			TLiferayConnectionDefinition.COMPONENT_NAME);
		assertComponentIsRegistered(
			TLiferayInputDefinition.class,
			TLiferayInputDefinition.COMPONENT_NAME);
		assertComponentIsRegistered(
			TLiferayOutputDefinition.class,
			TLiferayOutputDefinition.COMPONENT_NAME);
	}

}