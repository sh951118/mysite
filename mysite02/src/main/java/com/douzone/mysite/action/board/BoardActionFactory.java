package com.douzone.mysite.action.board;

import com.douzone.mysite.action.main.MainAction;
import com.douzone.web.action.Action;
import com.douzone.web.action.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		switch (actionName) {
		case "list" : return new ListAction();
		case "modifyform" : return new ModifyFormAction();
		case "view" : return new ViewAction();
		case "writeform" : return new WriteFormAction();
		case "modify" : return new ModifyAction();
		case "write" : return new WriteAction();
		case "delete" : return new DeleteAction();
		case "commentfrom" : return new CommentFromAction();
		case "comment" : return new CommentAction();
		case "find" : return new ListFind();
		default:return new MainAction();
		}
	}
}
