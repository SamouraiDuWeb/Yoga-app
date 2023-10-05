import { TestBed } from '@angular/core/testing';
import { expect } from '@jest/globals';

import { SessionService } from './session.service';
import {SessionInformation} from "../interfaces/sessionInformation.interface";

describe('SessionService', () => {
  let service: SessionService;
  const sessionInformation = {
    token: 'abc123',
    type: 'Bearer',
    id: 1,
    username: 'john.doe',
    firstName: 'John',
    lastName: 'Doe',
    admin: true
  } as SessionInformation;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SessionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should initialize with isLogged as false', () => {
    expect(service.isLogged).toBe(false);
  });

  it('should initialize with sessionInformation as undefined', () => {
    expect(service.sessionInformation).toBeUndefined();
  });

  it('should emit isLogged value when $isLogged is subscribed to', (done) => {
    const expectedIsLoggedValues = [false, true, false];

    service.$isLogged().subscribe((isLogged) => {
      expect(isLogged).toBe(expectedIsLoggedValues.shift());
      if (expectedIsLoggedValues.length === 0) {
        done();
      }
    });

    service.logIn({} as SessionInformation);
    service.logOut();
  });

  it('should set sessionInformation and isLogged to true when logIn is called', () => {
    service.logIn(sessionInformation);

    expect(service.sessionInformation).toBe(sessionInformation);
    expect(service.isLogged).toBe(true);
  });

  it('should set sessionInformation to undefined and isLogged to false when logOut is called', () => {

    service.logIn(sessionInformation);
    service.logOut();

    expect(service.sessionInformation).toBeUndefined();
    expect(service.isLogged).toBe(false);
  });
});
