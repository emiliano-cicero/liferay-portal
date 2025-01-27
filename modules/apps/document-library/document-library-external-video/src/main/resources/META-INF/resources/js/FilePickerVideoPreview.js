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

import {ClayInput} from '@clayui/form';
import ClayIcon from '@clayui/icon';
import ClayLoadingIndicator from '@clayui/loading-indicator';
import {useIsMounted} from 'frontend-js-react-web';
import {addParams, fetch} from 'frontend-js-web';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

import {useDebounceCallback} from './utils/hooks';
import validateUrl from './utils/validateUrl';

const FilePickerVideoPreview = ({
	externalVideoHTML = '',
	externalVideoURL = '',
	getDLExternalVideoFieldsURL,
	namespace,
	onFilePickCallback,
}) => {
	const inputName = 'externalVideoURLInput';
	const [error, setError] = useState('');
	const [loading, setLoading] = useState(false);
	const [url, setUrl] = useState(externalVideoURL);
	const [videoHtml, setVideoHtml] = useState(externalVideoHTML);
	const isMounted = useIsMounted();

	const [getFields] = useDebounceCallback((dlExternalVideoURL) => {
		fetch(
			addParams(
				{
					[`${namespace}dlExternalVideoURL`]: dlExternalVideoURL,
				},
				getDLExternalVideoFieldsURL
			)
		)
			.then((res) => res.json())
			.then((fields) => {
				if (isMounted()) {
					setLoading(false);
					setVideoHtml(fields.HTML);
					window[onFilePickCallback](fields);
				}
			})
			.catch(() => {
				if (isMounted()) {
					setLoading(false);
					setVideoHtml(externalVideoURL);
					setError(
						Liferay.Language.get(
							'sorry,-this-platform-is-not-supported'
						)
					);
				}
			});
	}, 500);

	const handleUrlChange = (event) => {
		const value = event.target.value.trim();

		setError('');
		setUrl(value);

		if (value && validateUrl(value)) {
			setLoading(true);
			getFields(value);
		}
		else {
			setLoading(false);
			setVideoHtml(externalVideoURL);
		}
	};

	return (
		<>
			<label htmlFor={inputName}>
				{Liferay.Language.get('video-url')}
			</label>
			<ClayInput
				id={inputName}
				onChange={handleUrlChange}
				placeholder="http://"
				type="text"
				value={url}
			/>
			<p className="form-text">
				{Liferay.Language.get('video-url-help')}
			</p>

			<div className="file-picker-preview-video">
				{videoHtml ? (
					<div
						className="file-picker-preview-video-aspect-ratio"
						dangerouslySetInnerHTML={{__html: videoHtml}}
					/>
				) : (
					<div className="file-picker-preview-video-aspect-ratio">
						<div className="file-picker-preview-video-placeholder">
							{loading ? (
								<ClayLoadingIndicator />
							) : (
								<>
									<ClayIcon symbol="video" />
									{error && (
										<div className="file-picker-preview-video-placeholder-text">
											{error}
										</div>
									)}
								</>
							)}
						</div>
					</div>
				)}
			</div>
		</>
	);
};

FilePickerVideoPreview.propTypes = {
	externalVideoHTML: PropTypes.string,
	externalVideoURL: PropTypes.string,
	getDLExternalVideoFieldsURL: PropTypes.string.isRequired,
	namespace: PropTypes.string.isRequired,
	onFilePickCallback: PropTypes.string.isRequired,
};

export default FilePickerVideoPreview;
