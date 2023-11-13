import { JwtInterceptor } from '../interceptors/jwt.interceptor';
import { SessionService } from '../services/session.service';
import { HttpRequest, HttpHandler } from '@angular/common/http';
import { SessionInformation } from '../interfaces/sessionInformation.interface';
import { expect } from '@jest/globals';

describe('JwtInterceptor', () => {
  let interceptor: JwtInterceptor;
  let sessionService: SessionService;

  beforeEach(() => {
    sessionService = new SessionService();
    interceptor = new JwtInterceptor(sessionService);
  });

  it('adds Authorization header when user logged in', () => {
    Object.defineProperty(sessionService, 'isLogged', { get: jest.fn(() => true) });
    Object.defineProperty(sessionService, 'sessionInformation', {
      get: jest.fn(() => ({
        token: 'abc123',
        type: 'Bearer',
        id: 1,
        username: 'johndoe@test.com',
        firstName: 'John',
        lastName: 'Doe',
        admin: true
      } as SessionInformation))
    });

    const request: HttpRequest<any> = new HttpRequest('GET', '/api/data');
    const next: HttpHandler = { handle: jest.fn() };

    interceptor.intercept(request, next);

    expect(next.handle).toHaveBeenCalledWith(
      expect.objectContaining({})
    );
  });

  it('skips Authorization header when user not logged in', () => {
    Object.defineProperty(sessionService, 'isLogged', { get: jest.fn(() => false) });
    const request: HttpRequest<any> = new HttpRequest('GET', '/api/data');
    const next: HttpHandler = { handle: jest.fn() };

    interceptor.intercept(request, next);

    expect(next.handle).toHaveBeenCalledWith(request);
  });
});
