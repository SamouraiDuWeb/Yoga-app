import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import {MatSnackBar, MatSnackBarModule} from '@angular/material/snack-bar';
import { RouterTestingModule, } from '@angular/router/testing';
import { expect } from '@jest/globals'; 
import { SessionService } from '../../../../services/session.service';

import { DetailComponent } from './detail.component';
import {Router} from "@angular/router";
import {TeacherService} from "../../../../services/teacher.service";
import {SessionApiService} from "../../services/session-api.service";
import {of} from "rxjs";


describe('DetailComponent', () => {
  let component: DetailComponent;
  let fixture: ComponentFixture<DetailComponent>;
  let sessionApiService: Partial<SessionApiService>;
  let teacherService: Partial<TeacherService>;

  const mockSessionService = {
    sessionInformation: {
      token: 'abc123',
      type: 'Bearer',
      id: 1,
      username: 'johndoe@test.com',
      firstName: 'John',
      lastName: 'Doe',
      admin: true
    }
  };

  beforeEach(async () => {
    sessionApiService = {
      delete: jest.fn().mockReturnValue(of({})),
      detail: jest.fn().mockReturnValue(of({})),
      participate: jest.fn().mockReturnValue(of({})),
      unParticipate: jest.fn().mockReturnValue(of({}))
    };

    teacherService = {
      detail: jest.fn().mockReturnValue(of({}))
    };

    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientModule,
        MatSnackBarModule,
        ReactiveFormsModule
      ],
      declarations: [DetailComponent],
      providers: [
        { provide: SessionService, useValue: mockSessionService },
        { provide: SessionApiService, useValue: sessionApiService },
        { provide: TeacherService, useValue: teacherService }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(DetailComponent);
    component = fixture.componentInstance;
  });

  it('is created', () => {
    expect(component).toBeTruthy();
  });

  it('invokes delete on component deletion', () => {
    const sessionApiServiceDeleteSpy = jest.spyOn(sessionApiService, 'delete');
    const matSnackBarOpenSpy = jest.spyOn(TestBed.inject(MatSnackBar), 'open');
    const routerNavigateSpy = jest.spyOn(TestBed.inject(Router), 'navigate');

    component.delete();

    expect(sessionApiServiceDeleteSpy).toHaveBeenCalled();
    expect(matSnackBarOpenSpy).toHaveBeenCalledWith('Session deleted !', 'Close', { duration: 3000 });
  });

  it('invokes participate on participation', () => {
    const sessionApiServiceParticipateSpy = jest.spyOn(sessionApiService, 'participate');

    component.participate();

    expect(sessionApiServiceParticipateSpy).toHaveBeenCalled();
  });

  it('invokes unParticipate on withdrawal', () => {
    const sessionApiServiceUnParticipateSpy = jest.spyOn(sessionApiService, 'unParticipate');

    component.unParticipate();

    expect(sessionApiServiceUnParticipateSpy).toHaveBeenCalled();
  });
});

