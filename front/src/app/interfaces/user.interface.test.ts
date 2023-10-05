import { User } from './user.interface';
import { expect } from '@jest/globals';

describe('User', () => {
  it('should create a User object', () => {
    const user: User = {
      id: 1,
      email: 'john.doe@example.com',
      lastName: 'Doe',
      firstName: 'John',
      admin: true,
      password: 'password123',
      createdAt: new Date(),
      updatedAt: new Date()
    };

    expect(user.id).toBe(1);
    expect(user.email).toBe('john.doe@example.com');
    expect(user.lastName).toBe('Doe');
    expect(user.firstName).toBe('John');
    expect(user.admin).toBe(true);
    expect(user.password).toBe('password123');
    expect(user.createdAt).toBeInstanceOf(Date);
    expect(user.updatedAt).toBeInstanceOf(Date);
  });
});
