package com.example.parcialBackendYanardi.Config;

import com.example.parcialBackendYanardi.Audit.Revision;
import org.hibernate.envers.RevisionListener;

public class CustomRevisionListener implements RevisionListener {

    public void newRevision(Object revisionEntity){
        final Revision revision = (Revision) revisionEntity;
    }
}
