/*
 * Copyright (C) 2001-2024 Food and Agriculture Organization of the
 * United Nations (FAO-UN), United Nations World Food Programme (WFP)
 * and United Nations Environment Programme (UNEP)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301, USA
 *
 * Contact: Jeroen Ticheler - FAO - Viale delle Terme di Caracalla 2,
 * Rome - Italy. email: geonetwork@osgeo.org
 */

package org.fao.geonet.translations;

import org.fao.geonet.kernel.setting.SettingManager;
import org.fao.geonet.kernel.setting.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Component
public class TranslationFactory {
    private SettingManager settingManager;

    private List<ITranslationService> translationServiceList;

    @Autowired
    public TranslationFactory(SettingManager settingManager, List<ITranslationService> translationServiceList) {
        this.settingManager = settingManager;
        this.translationServiceList = translationServiceList;
    }

    public Optional<ITranslationService> getTranslationService() {
        String translationProvider = settingManager.getValue(Settings.SYSTEM_TRANSLATION_PROVIDER);

        if (StringUtils.hasLength(translationProvider)) {
            return translationServiceList.stream().filter(t -> t.name().equals(translationProvider)).findFirst();

        } else {
            return Optional.ofNullable(null);
        }
    }
}
