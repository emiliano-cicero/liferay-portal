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

import {ADD_ITEM} from '../../actions/types';
import * as undoLayoutDataAction from './undoLayoutDataAction';

const UNDO_ACTIONS = {
	[ADD_ITEM]: undoLayoutDataAction,
};

export function canUndoAction(action) {
	return Object.keys(UNDO_ACTIONS).includes(action.type) && !action.isUndo;
}

export function getDerivedStateForUndo({action, state, type}) {
	const undoAction = UNDO_ACTIONS[type];

	return {...undoAction.getDerivedStateForUndo({action, state}), type};
}

export function undoAction({action, store}) {
	const {type} = action;

	const undoAction = UNDO_ACTIONS[type];

	return undoAction.undoAction({action, store});
}
