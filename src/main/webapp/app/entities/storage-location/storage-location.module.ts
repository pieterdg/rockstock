import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RockstockSharedModule } from 'app/shared/shared.module';
import { StorageLocationComponent } from './storage-location.component';
import { StorageLocationDetailComponent } from './storage-location-detail.component';
import { StorageLocationUpdateComponent } from './storage-location-update.component';
import { StorageLocationDeleteDialogComponent } from './storage-location-delete-dialog.component';
import { storageLocationRoute } from './storage-location.route';

@NgModule({
  imports: [RockstockSharedModule, RouterModule.forChild(storageLocationRoute)],
  declarations: [
    StorageLocationComponent,
    StorageLocationDetailComponent,
    StorageLocationUpdateComponent,
    StorageLocationDeleteDialogComponent
  ],
  entryComponents: [StorageLocationDeleteDialogComponent]
})
export class RockstockStorageLocationModule {}
