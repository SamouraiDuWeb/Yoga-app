import { Teacher } from './teacher.interface';
import { expect } from '@jest/globals';

describe('Teacher', () => {
  it('should create a Teacher object', () => {
    const teacher: Teacher = {
      id: 1,
      lastName: 'Doe',
      firstName: 'John',
      createdAt: new Date(),
      updatedAt: new Date()
    };

    expect(teacher.id).toBe(1);
    expect(teacher.lastName).toBe('Doe');
    expect(teacher.firstName).toBe('John');
    expect(teacher.createdAt).toBeInstanceOf(Date);
    expect(teacher.updatedAt).toBeInstanceOf(Date);
  });
});
